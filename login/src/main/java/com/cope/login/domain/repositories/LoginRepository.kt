package com.cope.login.domain.repositories

import com.cope.core.constants.Token

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface LoginRepository {

    suspend fun login(username: String, password: String): Token
}
