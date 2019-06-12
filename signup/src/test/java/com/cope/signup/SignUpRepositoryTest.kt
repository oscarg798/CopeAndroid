package com.cope.signup

import com.cope.core.models.User
import com.cope.signup.data.entities.APIUser
import com.cope.signup.data.repository.SignUpRepositoryImpl
import com.cope.signup.data.service.SignupService
import com.cope.signup.domain.entities.SignUpParams
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpRepositoryTest: MockableTest {

    @MockK
    lateinit var signupService: SignupService

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            signupService.signUp(any())
        }.answers {
            APIUser("1","2","3")
        }
    }

    @Test
    fun `should singUp user`(){
        val repository  = given {
            SignUpRepositoryImpl(signupService)
        }

        val result = whenever {
            runBlocking {
                repository.signUp(SignUpParams("1","2","3"))
            }
        }

        then {
            result `should equal` User("1","2","3")
        }
    }
}
