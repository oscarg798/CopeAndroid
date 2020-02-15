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

package co.com.cope.signup.di

import co.com.cope.signup.SignUpContract
import co.com.cope.signup.SignUpPresenter
import co.com.cope.signup.data.repository.SignUpRepositoryImpl
import co.com.cope.signup.data.service.SignupService
import co.com.cope.signup.domain.entities.SignUpParams
import co.com.cope.signup.domain.interactor.SignUpInteractor
import co.com.cope.signup.domain.repositories.SignUpRepository
import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.interactor.Interactor
import com.cope.core.models.User
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@Module
class SignUpModule {

    @Provides
    fun provideSignUpService(retrofit: Retrofit): SignupService {
        return retrofit.create(SignupService::class.java)
    }

    @Provides
    fun provideSignUpRepository(signUpService: SignupService): SignUpRepository {
        return SignUpRepositoryImpl(signUpService)
    }

    @Provides
    fun provideSignUpInteractor(signUpRepository: SignUpRepository): Interactor<User, SignUpParams> {
        return SignUpInteractor(signUpRepository)
    }

    @Provides
    fun provideSignUpPresenter(
        signUpInteractor: Interactor<User, SignUpParams>,
        @Named(COROUTINE_IO_CONTEXT_PROVIDER)
        coroutineContextProvider: CoroutineContextProvider
    ): SignUpContract.Presenter {
        return SignUpPresenter(
            signUpInteractor,
            coroutineContextProvider
        )
    }
}