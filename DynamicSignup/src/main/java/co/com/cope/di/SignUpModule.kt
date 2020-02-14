package co.com.cope.di

import co.com.cope.SignUpContract
import co.com.cope.SignUpPresenter
import co.com.cope.data.repository.SignUpRepositoryImpl
import co.com.cope.data.service.SignupService
import co.com.cope.domain.entities.SignUpParams
import co.com.cope.domain.interactor.SignUpInteractor
import co.com.cope.domain.repositories.SignUpRepository
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.CoroutineContextProvider
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
        return SignUpPresenter(signUpInteractor, coroutineContextProvider)
    }
}
