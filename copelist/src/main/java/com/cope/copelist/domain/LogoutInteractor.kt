package com.cope.copelist.domain

import com.cope.core.interactor.Interactor
import com.cope.core.models.None
import com.cope.core.repositories.LocalStorageRepository

class LogoutInteractor(private val localStorageRepository: LocalStorageRepository) :
    Interactor<Unit, None> {

    override suspend fun invoke(params: None) {
        localStorageRepository.clear()
    }
}