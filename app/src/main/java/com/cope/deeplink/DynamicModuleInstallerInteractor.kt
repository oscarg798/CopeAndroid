package com.cope.deeplink

import android.content.Context
import android.content.Intent
import android.util.Log
import com.cope.R
import com.cope.core.interactor.Interactor
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

typealias FeatureName = String

class DynamicModuleInstallerInteractor(private val context: Context) :
    Interactor<Unit, FeatureName> {

    override suspend fun invoke(params: FeatureName) {
        val splitInstallManager = SplitInstallManagerFactory.create(context)

        if (splitInstallManager.installedModules.contains(params)) {
            return
        }

        installDynamicFeatureAsync(splitInstallManager, params).await()
    }

    private fun installDynamicFeatureAsync(
        splitInstallManager: SplitInstallManager,
        featureName: FeatureName
    ): Deferred<Unit> {
        val deferred = CompletableDeferred<Unit>()

        val request = SplitInstallRequest.newBuilder()
            .addModule(featureName)
            .build()

        splitInstallManager.deferredInstall(listOf(featureName))
            .addOnSuccessListener {
                deferred.complete(Unit)
            }.addOnFailureListener {
                if (it is SplitInstallException) {
                    handleSplitInstallException(deferred, it, featureName)
                } else {
                    deferred.completeExceptionally(it)
                }
            }

        return deferred
    }

    private fun handleSplitInstallException(
        deferred: CompletableDeferred<Unit>,
        it: SplitInstallException,
        featureName: FeatureName
    ) {
        deferred.completeExceptionally(
            when (it.errorCode) {
                SplitInstallErrorCode.NETWORK_ERROR -> DynamicModuleInstallationException.NetworkError(
                    featureName,
                    it
                )
                else -> DynamicModuleInstallationException.InstallationFailed(
                    featureName,
                    it
                )
            }
        )
    }

}