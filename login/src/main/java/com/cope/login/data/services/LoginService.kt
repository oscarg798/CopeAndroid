package com.cope.login.data.services

import com.cope.login.data.entities.APiLoginParams
import com.cope.login.data.entities.UserLoginReponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface LoginService {

    @POST("auth")
    suspend fun login(@Body aPiLoginParams: APiLoginParams): UserLoginReponse
}
