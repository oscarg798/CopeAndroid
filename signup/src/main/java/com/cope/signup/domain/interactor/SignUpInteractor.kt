package com.cope.signup.domain.interactor

import com.cope.core.interactor.Interactor
import com.cope.core.models.User
import com.cope.signup.domain.entities.SignUpParams
import com.cope.signup.domain.repositories.SignUpRepository

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpInteractor(private val signUpRepository: SignUpRepository): Interactor<User,SignUpParams>{

    override suspend fun invoke(params: SignUpParams): User {
        return signUpRepository.signUp(params)
    }
}
