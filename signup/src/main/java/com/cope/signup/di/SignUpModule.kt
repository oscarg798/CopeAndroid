package com.cope.signup.di

import com.cope.core.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.CoroutineContextProvider
import com.cope.core.interactor.Interactor
import com.cope.core.models.User
import com.cope.signup.SignUpContract
import com.cope.signup.SignUpPresenter
import com.cope.signup.data.repository.SignUpRepositoryImpl
import com.cope.signup.data.service.SignupService
import com.cope.signup.domain.entities.SignUpParams
import com.cope.signup.domain.interactor.SignUpInteractor
import com.cope.signup.domain.repositories.SignUpRepository
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
        return SignUpPresenter(signUpInteractor, coroutineContextProvider)
    }
}
