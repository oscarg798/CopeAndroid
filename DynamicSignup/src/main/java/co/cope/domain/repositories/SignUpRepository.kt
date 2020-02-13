package co.cope.domain.repositories

import com.cope.core.models.User
import co.cope.domain.entities.SignUpParams

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface SignUpRepository {

    suspend fun signUp(params: SignUpParams): User
}
