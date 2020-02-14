package com.cope.core.di

import android.content.Context
import com.cope.core.constants.BASE_URL
import com.cope.core.constants.COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.CoroutineContextProvider
import com.cope.core.DynamicFeatureMappers
import com.cope.core.featureflags.FirebaseRemoteConfigInitializator
import com.cope.core.interactor.InitFirebaseRemoteConfigUseCase
import com.cope.core.interactor.Interactor
import com.cope.logger.CopeLogger
import com.cope.logger.CountlyLoggerProcessor
import com.cope.logger.Logger
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import ly.count.android.sdk.Countly
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@Module
class CoreModule(private val appContext: Context) {

    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @CoreComponentScope
    @Named(COROUTINE_IO_CONTEXT_PROVIDER)
    @Provides
    fun provideIOCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider(Dispatchers.Main, Dispatchers.IO)
    }

    @CoreComponentScope
    @Named(COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER)
    @Provides
    fun provideComputationCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider(Dispatchers.Main, Dispatchers.Default)
    }

    @CoreComponentScope
    @Provides
    fun provideBaseUrl(): String {
        return BASE_URL
    }

    @Provides
    fun provideCountly(): Countly {
        return Countly.sharedInstance()
    }

    @CoreComponentScope
    @Provides
    fun provideLogger(context: Context, countly: Countly): Logger {
        return CopeLogger(listOf(CountlyLoggerProcessor(context, countly)))
    }

    @CoreComponentScope
    @Provides
    fun provideInitFirebaseRemoteConfigUseCase(initializator: FirebaseRemoteConfigInitializator): Interactor<Unit, Unit> {
        return InitFirebaseRemoteConfigUseCase(initializator)
    }

    @CoreComponentScope
    @Provides
    fun provideDynamicFeatureMappers(): DynamicFeatureMappers{
        return DynamicFeatureMappers()
    }
}
