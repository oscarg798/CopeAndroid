package com.cope.core.featureflags

import com.cope.core.exceptions.FirebaseRemoteConfigInitializationException
import com.cope.core.models.FeatureFlag
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

class FirebaseRemoteConfigFeatureFlagHandler : FeatureFlagHandler,
    FirebaseRemoteConfigInitializator {

    private val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(FETCH_INTERVAL)
            .build()

        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    override fun activateAsync(): Deferred<Unit> {
        val deferred = CompletableDeferred<Unit>()
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    deferred.complete(Unit)
                } else {
                    deferred.completeExceptionally(FirebaseRemoteConfigInitializationException())
                }
            }

        return deferred
    }

    override fun isFeatureEnabled(featureFlag: FeatureFlag): Boolean {
        return remoteConfig.getBoolean(featureFlag.key)
    }

    companion object {
        private const val FETCH_INTERVAL = 3L
    }
}