package com.cope.login.domain.interactor

import com.cope.core.constants.Token
import com.cope.core.interactor.Interactor
import com.cope.login.domain.entities.LoginParams
import com.cope.login.domain.repositories.LoginRepository

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class LoginInteractor(private val loginRepository: LoginRepository) : Interactor<Token, LoginParams> {

    override suspend fun invoke(params: LoginParams): Token {
        return loginRepository.login(params.username, params.password)
    }
}
