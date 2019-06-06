package com.cope.signup

import com.cope.core.models.User
import com.cope.signup.data.entities.APIUser
import com.cope.signup.data.mapper.UserMapper
import org.amshove.kluent.`should equal`
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class UserMapperTest  {

    @Test
    fun `should map from api user`(){
        val apiUser = given {
            APIUser("1","2","3","4")
        }

        val result = whenever {
            UserMapper.map(apiUser)
        }

        then {
            result `should equal` User("1","2","3","4")
        }
    }
}
