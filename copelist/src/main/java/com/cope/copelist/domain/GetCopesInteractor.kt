package com.cope.copelist.domain

import com.cope.core.interactor.Interactor
import com.cope.core.models.Cope
import com.cope.core.models.None
import com.cope.core.repositories.CopeRepository

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class GetCopesInteractor(private val copeRepository: CopeRepository) : Interactor<List<Cope>,None> {

    override suspend fun invoke(params: None): List<Cope> {
        return copeRepository.getCopes()
    }
}
