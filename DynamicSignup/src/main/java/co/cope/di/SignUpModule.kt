package co.cope.di

import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.CoroutineContextProvider
import com.cope.core.interactor.Interactor
import com.cope.core.models.User
import co.cope.SignUpContract
import co.cope.SignUpPresenter
import co.cope.data.repository.SignUpRepositoryImpl
import co.cope.data.service.SignupService
import co.cope.domain.entities.SignUpParams
import co.cope.domain.interactor.SignUpInteractor
import co.cope.domain.repositories.SignUpRepository
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
