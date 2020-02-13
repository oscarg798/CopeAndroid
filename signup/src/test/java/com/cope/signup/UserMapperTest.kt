package com.cope.signup

import com.cope.core.models.User
import co.cope.data.entities.APIUser
import co.cope.data.mapper.UserMapper
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class UserMapperTest {

    @Test
    fun `should map from api user`() {
        val apiUser = given {
            co.cope.data.entities.APIUser("1", "2", "3")
        }

        val result = whenever {
            co.cope.data.mapper.UserMapper.map(apiUser)
        }

        then {
            result `should equal` User("1", "2", "3")
        }
    }
}
