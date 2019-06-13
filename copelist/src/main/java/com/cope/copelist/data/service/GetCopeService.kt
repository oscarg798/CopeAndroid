package com.cope.copelist.data.service

import com.cope.copelist.data.entities.APICope
import retrofit2.http.GET

/**
 * @author Oscar Gallon on 2019-06-11.
 */
interface GetCopeService {

    @GET("cope")
    suspend fun getCopes(): List<APICope>
}
