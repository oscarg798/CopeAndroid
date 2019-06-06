package com.cope.login.di

import com.cope.core.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.CoroutineContextProvider
import com.cope.core.Token
import com.cope.core.interactor.Interactor
import com.cope.login.LoginActivityContract
import com.cope.login.domain.entities.LoginParams
import com.cope.login.LoginPresenter
import com.cope.login.domain.repositories.LoginRepository
import com.cope.login.data.LoginRepositoryImpl
import com.cope.login.data.services.LoginService
import com.cope.login.domain.interactor.LoginInteractor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@Module
class LoginModule {

    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }
    @Provides
    fun provideLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepositoryImpl(loginService)
    }

    @Provides
    fun provideLoginInteractor(loginRepository: LoginRepository): Interactor<Token, LoginParams> {
        return LoginInteractor(loginRepository)
    }

    @Provides
    fun provideLoginPresenter(
        loginInteractor: Interactor<Token, LoginParams>,
        @Named(COROUTINE_IO_CONTEXT_PROVIDER)
        coroutineContextProvider: CoroutineContextProvider
    ): LoginActivityContract.Presenter {
        return LoginPresenter(loginInteractor, coroutineContextProvider)
    }
}
