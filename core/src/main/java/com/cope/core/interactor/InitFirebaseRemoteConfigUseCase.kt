package com.cope.core.interactor

import com.cope.core.featureflags.FirebaseRemoteConfigInitializator

class InitFirebaseRemoteConfigUseCase(private val firebaseRemoteConfigInitializator: FirebaseRemoteConfigInitializator) :
    Interactor<Unit, Unit> {

    override suspend fun invoke(params: Unit) {
        firebaseRemoteConfigInitializator.activateAsync().await()
    }
}