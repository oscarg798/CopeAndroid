package com.cope.core.repositories

import com.cope.core.models.Cope

/**
 * @author Oscar Gallon on 2019-06-11.
 */
interface CopeRepository {

    suspend fun getCopes(): List<Cope>
}
