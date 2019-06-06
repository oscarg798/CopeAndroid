package com.cope.core.di

import android.content.Context
import com.cope.core.BASE_URL
import com.cope.core.COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER
import com.cope.core.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@Module
class CoreModule(private val appContext: Context) {


    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @Named(COROUTINE_IO_CONTEXT_PROVIDER)
    @Provides
    fun provideIOCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider(Dispatchers.Main, Dispatchers.IO)
    }

    @Named(COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER)
    @Provides
    fun provideComputationCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider(Dispatchers.Main, Dispatchers.Default)
    }

    @Provides
    fun provideBaseUrl(): String {
        return BASE_URL
    }
}
