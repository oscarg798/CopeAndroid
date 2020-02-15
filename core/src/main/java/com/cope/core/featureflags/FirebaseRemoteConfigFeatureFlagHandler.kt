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