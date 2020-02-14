package co.com.cope.signup.domain.repositories

import co.com.cope.signup.domain.entities.SignUpParams
import com.cope.core.models.User

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface SignUpRepository {

    suspend fun signUp(params: SignUpParams): User
}
