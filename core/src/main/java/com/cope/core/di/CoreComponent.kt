package com.cope.core.di

import android.content.Context
import com.cope.core.CoreApplication
import com.cope.core.CoroutineContextProvider
import com.cope.core.DynamicFeatureMappers
import com.cope.core.constants.COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.constants.FEATURE_FLAG_HANDLER
import com.cope.core.constants.FIREBASE_REMOTE_CONFIG_FEATURE_FLAG_HANDLER
import com.cope.core.featureflags.FeatureFlagHandler
import com.cope.core.featureflags.FirebaseRemoteConfigInitializator
import com.cope.core.interactor.Interactor
import com.cope.core.repositories.LocalStorageRepository
import com.cope.logger.Logger
import dagger.Component
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@CoreComponentScope
@Component(modules = [CoreModule::class, NetworkModule::class, RepositoryModule::class, FeatureFlagModule::class])
interface CoreComponent {

    fun inject(coreApplication: CoreApplication)

    @Named(COROUTINE_IO_CONTEXT_PROVIDER)
    fun provideIOCoroutineContextProvider(): CoroutineContextProvider

    @Named(COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER)
    fun provideComputationCoroutineContextProvider(): CoroutineContextProvider

    fun provideRetrofit(): Retrofit

    fun provideLocalStorageRepository(): LocalStorageRepository

    fun provideLogger(): Logger

    @Named(FIREBASE_REMOTE_CONFIG_FEATURE_FLAG_HANDLER)
    fun provideFirebaseRemoteConfigFeatureHandler(): FeatureFlagHandler

    @Named(FEATURE_FLAG_HANDLER)
    fun provideCopeFeatureFlagHandler(): FeatureFlagHandler

    fun provideFirebaseRemoteConfigInitializator(): FirebaseRemoteConfigInitializator

    fun provideInitFirebaseRemoteConfigUseCase(): Interactor<Unit, Unit>

    fun provideDynamicFeatureMappers(): DynamicFeatureMappers

    fun provideContext(): Context
}
