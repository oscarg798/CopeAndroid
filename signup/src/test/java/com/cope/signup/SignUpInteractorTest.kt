package com.cope.signup

import com.cope.core.models.User
import com.cope.signup.domain.entities.SignUpParams
import com.cope.signup.domain.interactor.SignUpInteractor
import com.cope.signup.domain.repositories.SignUpRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpInteractorTest : MockableTest {

    @MockK
    lateinit var signUpRepository: SignUpRepository

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
            SignUpInteractor(signUpRepository)
        }

        val result = whenever {
            runBlocking {
                interactor(SignUpParams("1", "2", "3"))
            }
        }

        then {
            result `should equal` User("1", "2", "3")
        }
    }
}
