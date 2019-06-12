package com.cope.signup.data.mapper

import com.cope.core.models.User
import com.cope.signup.data.entities.APIUser

/**
 * @author Oscar Gallon on 2019-06-06.
 */
object UserMapper {

    fun map(apiUser: APIUser): User {
        return User(apiUser._id, apiUser.name, apiUser.email)
    }
}
