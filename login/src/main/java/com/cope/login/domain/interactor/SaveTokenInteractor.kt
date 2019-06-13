package com.cope.login.domain.interactor

import com.cope.core.constants.TOKEN
import com.cope.core.constants.Token
import com.cope.core.interactor.Interactor
import com.cope.core.repositories.LocalStorageRepository

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class SaveTokenInteractor(private val localStorageRepository: LocalStorageRepository) :
    Interactor<Unit, Token> {

    override suspend fun invoke(params: Token) {
        localStorageRepository.saveData(TOKEN, params)
    }
}
