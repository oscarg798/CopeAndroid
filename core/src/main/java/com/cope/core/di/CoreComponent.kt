package com.cope.core.di

import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.repositories.LocalStorageRepository
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@Component(modules = [CoreModule::class, NetworkModule::class, RepositoryModule::class])
interface CoreComponent {

    @Named(COROUTINE_IO_CONTEXT_PROVIDER)
    fun provideIOCoroutineContextProvider(): CoroutineContextProvider

    @Named(COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER)
    fun provideComputationCoroutineContextProvider(): CoroutineContextProvider

    fun provideRetrofit(): Retrofit

    fun provideLocalStorageRepository(): LocalStorageRepository
}
