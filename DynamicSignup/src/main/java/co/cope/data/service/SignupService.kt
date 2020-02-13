package co.cope.data.service

import co.cope.data.entities.APISignUpParams
import co.cope.data.entities.APIUser
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface SignupService {

    @POST("user")
    suspend fun signUp(@Body apiSignUpParams: APISignUpParams): APIUser
}
