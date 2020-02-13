package com.cope.signup

import com.cope.core.models.User
import co.cope.data.entities.APIUser
import co.cope.data.repository.SignUpRepositoryImpl
import co.cope.data.service.SignupService
import co.cope.domain.entities.SignUpParams
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpRepositoryTest : MockableTest {

    @MockK
    lateinit var signupService: co.cope.data.service.SignupService

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            signupService.signUp(any())
        }.answers {
            co.cope.data.entities.APIUser("1", "2", "3")
        }
    }

    @Test
    fun `should singUp user`() {
        val repository = given {
            co.cope.data.repository.SignUpRepositoryImpl(signupService)
        }

        val result = whenever {
            runBlocking {
                repository.signUp(co.cope.domain.entities.SignUpParams("1", "2", "3"))
            }
        }

        then {
            result `should equal` User("1", "2", "3")
        }
    }
}
