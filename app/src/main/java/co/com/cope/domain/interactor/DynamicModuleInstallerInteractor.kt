package co.com.cope.domain.interactor

import android.content.Context
import co.com.cope.domain.exceptions.DynamicModuleInstallationException
import com.cope.core.DynamicFeatureMap
import com.cope.core.featureName
import com.cope.core.interactor.Interactor
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

class DynamicModuleInstallerInteractor(private val context: Context) :
    Interactor<Unit, DynamicFeatureMap> {

    override suspend fun invoke(params: DynamicFeatureMap) {
        val splitInstallManager = SplitInstallManagerFactory.create(context)

        if (splitInstallManager.installedModules.contains(params.featureName())) {
            return
        }

        installDynamicFeatureAsync(splitInstallManager, params.featureName()).await()
    }

    private fun installDynamicFeatureAsync(
        splitInstallManager: SplitInstallManager,
        featureName: String
    ): Deferred<Unit> {
        val deferred = CompletableDeferred<Unit>()

        val request = SplitInstallRequest.newBuilder()
            .addModule(featureName)
            .build()

        splitInstallManager.startInstall(request)
            .addOnSuccessListener {
                deferred.complete(Unit)
            }.addOnFailureListener {
                if (it is SplitInstallException) {
                    FirebaseCrashlytics.getInstance().recordException(it)
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
        featureName: String
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