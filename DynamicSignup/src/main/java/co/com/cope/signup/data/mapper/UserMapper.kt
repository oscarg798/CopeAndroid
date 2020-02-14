package co.com.cope.signup.data.mapper

import co.com.cope.signup.data.entities.APIUser
import com.cope.core.models.User

/**
 * @author Oscar Gallon on 2019-06-06.
 */
object UserMapper {

    fun map(apiUser: APIUser): User {
        return User(apiUser._id, apiUser.name, apiUser.email)
    }
}
