package com.cope.login.data

import com.cope.core.Token
import com.cope.login.data.entities.APiLoginParams
import com.cope.login.data.services.LoginService
import com.cope.login.domain.repositories.LoginRepository

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class LoginRepositoryImpl(private val loginService: LoginService) :
    LoginRepository {

    override suspend fun login(username: String, password: String): Token {
        return loginService.login(APiLoginParams(username, password)).token
    }
}
