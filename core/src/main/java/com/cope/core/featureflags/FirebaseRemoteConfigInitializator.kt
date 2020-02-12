package com.cope.core.featureflags

import kotlinx.coroutines.Deferred

interface FirebaseRemoteConfigInitializator {

    fun activateAsync(): Deferred<Unit>
}