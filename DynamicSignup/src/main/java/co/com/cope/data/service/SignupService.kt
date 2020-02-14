package co.com.cope.data.service

import co.com.cope.data.entities.APISignUpParams
import co.com.cope.data.entities.APIUser
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface SignupService {

    @POST("user")
    suspend fun signUp(@Body apiSignUpParams: APISignUpParams): APIUser
}
