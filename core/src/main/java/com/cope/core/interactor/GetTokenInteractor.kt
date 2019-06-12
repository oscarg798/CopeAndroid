package com.cope.core.interactor

import com.cope.core.constants.TOKEN
import com.cope.core.constants.Token
import com.cope.core.models.None
import com.cope.core.repositories.LocalStorageRepository

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class GetTokenInteractor(private val localStorageRepository: LocalStorageRepository) : Interactor<Token, None> {

    override suspend fun invoke(params: None): Token {
        return localStorageRepository.getData(TOKEN, String::class.java)
    }
}
