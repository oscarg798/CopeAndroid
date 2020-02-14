package co.com.cope.domain.interactor

import co.com.cope.domain.entities.SignUpParams
import co.com.cope.domain.repositories.SignUpRepository
import com.cope.core.interactor.Interactor
import com.cope.core.models.User

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpInteractor(private val signUpRepository: SignUpRepository) : Interactor<User, SignUpParams> {

    override suspend fun invoke(params: SignUpParams): User {
        return signUpRepository.signUp(params)
    }
}
