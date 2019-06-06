package com.cope.signup.data.repository

import com.cope.core.models.User
import com.cope.signup.data.entities.APISignUpParams
import com.cope.signup.data.mapper.UserMapper
import com.cope.signup.data.service.SignupService
import com.cope.signup.domain.entities.SignUpParams
import com.cope.signup.domain.repositories.SignUpRepository

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpRepositoryImpl(private val signUpService: SignupService) : SignUpRepository {

    override suspend fun signUp(params: SignUpParams): User {
        val apiUser = signUpService.signUp(APISignUpParams(params.email, params.password, params.name))
        return UserMapper.map(apiUser)
    }
}
