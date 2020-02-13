package co.cope.data.repository

import com.cope.core.models.User
import co.cope.data.entities.APISignUpParams
import co.cope.data.mapper.UserMapper
import co.cope.data.service.SignupService
import co.cope.domain.entities.SignUpParams
import co.cope.domain.repositories.SignUpRepository

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
