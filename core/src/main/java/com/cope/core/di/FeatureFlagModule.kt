package com.cope.core.di

import com.cope.core.constants.FEATURE_FLAG_HANDLER
import com.cope.core.constants.FIREBASE_REMOTE_CONFIG_FEATURE_FLAG_HANDLER
import com.cope.core.featureflags.CopeFeatureFlagHandler
import com.cope.core.featureflags.FeatureFlagHandler
import com.cope.core.featureflags.FirebaseRemoteConfigFeatureFlagHandler
import com.cope.core.featureflags.FirebaseRemoteConfigInitializator
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class FeatureFlagModule {

    private val firebaseRemoteConfigFeatureFlagHandler: FirebaseRemoteConfigFeatureFlagHandler =
        FirebaseRemoteConfigFeatureFlagHandler()

    @Named(FIREBASE_REMOTE_CONFIG_FEATURE_FLAG_HANDLER)
    @Provides
    fun provideFirebaseRemoteConfigFeatureHandler(): FeatureFlagHandler {
        return firebaseRemoteConfigFeatureFlagHandler
    }

    @Named(FEATURE_FLAG_HANDLER)
    @Provides
    fun provideCopeFeatureFlagHandler(@Named(FIREBASE_REMOTE_CONFIG_FEATURE_FLAG_HANDLER) firebaseRemoteConfigFeatureFlagHandler: FeatureFlagHandler): FeatureFlagHandler {
        return CopeFeatureFlagHandler(listOf(firebaseRemoteConfigFeatureFlagHandler))
    }

    @Provides
    fun provideFirebaseRemoteConfigInitializator(): FirebaseRemoteConfigInitializator {
        return firebaseRemoteConfigFeatureFlagHandler
    }
}