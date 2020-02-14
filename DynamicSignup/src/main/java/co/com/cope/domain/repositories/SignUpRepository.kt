package co.com.cope.domain.repositories

import co.com.cope.domain.entities.SignUpParams
import com.cope.core.models.User

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface SignUpRepository {

    suspend fun signUp(params: SignUpParams): User
}
