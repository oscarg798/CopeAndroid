package com.cope.signup

import com.cope.core.models.User
import co.cope.domain.entities.SignUpParams
import co.cope.domain.interactor.SignUpInteractor
import co.cope.domain.repositories.SignUpRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpInteractorTest : MockableTest {

    @MockK
    lateinit var signUpRepository: co.cope.domain.repositories.SignUpRepository

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            signUpRepository.signUp(any())
        }.answers {
            User("1", "2", "3")
        }
    }

    @Test
    fun `should sign up`() {
        val interactor = given {
            co.cope.domain.interactor.SignUpInteractor(signUpRepository)
        }

        val result = whenever {
            runBlocking {
                interactor(co.cope.domain.entities.SignUpParams("1", "2", "3"))
            }
        }

        then {
            result `should equal` User("1", "2", "3")
        }
    }
}
