package com.cope.copelist.domain.repository

import com.cope.copelist.data.mapper.APICopeMapper
import com.cope.copelist.data.service.GetCopeService
import com.cope.core.models.Cope
import com.cope.core.repositories.CopeRepository

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeRepositoryImpl(
    private val getCopeService: GetCopeService,
    private val apiCopeMapper: APICopeMapper
) : CopeRepository {

    override suspend fun getCopes(): List<Cope> {
        val apiCopes = getCopeService.getCopes()

        return apiCopes.map {
            apiCopeMapper.map(it)
        }
    }
}
