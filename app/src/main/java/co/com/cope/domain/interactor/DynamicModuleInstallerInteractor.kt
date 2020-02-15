/*
 * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 * This file is part of Cope.
 * Cope is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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