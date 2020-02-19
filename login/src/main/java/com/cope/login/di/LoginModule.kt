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

package com.cope.login.di

import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.constants.Token
import com.cope.core.interactor.Interactor
import com.cope.core.repositories.LocalStorageRepository
import com.cope.login.LoginActivityContract
import com.cope.login.LoginPresenter
import com.cope.login.data.LoginRepositoryImpl
import com.cope.login.data.services.LoginService
import com.cope.login.domain.entities.LoginParams
import com.cope.login.domain.interactor.LoginInteractor
import com.cope.login.domain.interactor.SaveTokenInteractor
import com.cope.login.domain.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@Module
object LoginModule {

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
    fun provideSaveTokenInteractor(localStorageRepository: LocalStorageRepository): Interactor<Unit, Token> {
        return SaveTokenInteractor(localStorageRepository)
    }

    @Provides
    fun provideLoginPresenter(
        loginInteractor: Interactor<Token, LoginParams>,
        saveTokenInteractor: Interactor<Unit, Token>,
        @Named(COROUTINE_IO_CONTEXT_PROVIDER)
        coroutineContextProvider: CoroutineContextProvider
    ): LoginActivityContract.Presenter {
        return LoginPresenter(loginInteractor, saveTokenInteractor, coroutineContextProvider)
    }
}
