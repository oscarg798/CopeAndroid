package com.cope.di

import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.constants.Token
import com.cope.core.interactor.GetTokenInteractor
import com.cope.core.interactor.Interactor
import com.cope.core.models.None
import com.cope.core.repositories.LocalStorageRepository
import com.cope.splash.SplashContract
import com.cope.splash.SplashPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-11.
 */
@Module
class SplashModule {

    @Provides
    fun provideGetTokenInteractor(localStorageRepository: LocalStorageRepository): Interactor<Token, None> {
        return GetTokenInteractor(localStorageRepository)
    }

    @Provides
    fun provideSplashPresenter(
        getTokenInteractor: Interactor<Token, None>,
        @Named(COROUTINE_IO_CONTEXT_PROVIDER)
        coroutineContextProvider: CoroutineContextProvider
    ): SplashContract.Presenter {
        return SplashPresenter(getTokenInteractor, coroutineContextProvider)
    }
}
