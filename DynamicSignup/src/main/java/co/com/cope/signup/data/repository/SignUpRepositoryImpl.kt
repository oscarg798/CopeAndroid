package co.com.cope.signup.data.repository

import co.com.cope.signup.data.entities.APISignUpParams
import co.com.cope.signup.data.mapper.UserMapper
import co.com.cope.signup.data.service.SignupService
import co.com.cope.signup.domain.entities.SignUpParams
import co.com.cope.signup.domain.repositories.SignUpRepository
import com.cope.core.models.User

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpRepositoryImpl(private val signUpService: SignupService) :
    SignUpRepository {

    override suspend fun signUp(params: SignUpParams): User {
        val apiUser = signUpService.signUp(
            APISignUpParams(
                params.email,
                params.password,
                params.name
            )
        )
        return UserMapper.map(apiUser)
    }
}
