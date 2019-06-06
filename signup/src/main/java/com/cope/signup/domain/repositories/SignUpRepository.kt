package com.cope.signup.domain.repositories

import com.cope.core.models.User
import com.cope.signup.domain.entities.SignUpParams

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface SignUpRepository {

    suspend fun signUp(params: SignUpParams): User
}
