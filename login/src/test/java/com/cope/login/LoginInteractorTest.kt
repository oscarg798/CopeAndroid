package com.cope.login

import com.cope.login.domain.entities.LoginParams
import com.cope.login.domain.interactor.LoginInteractor
import com.cope.login.domain.repositories.LoginRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class LoginInteractorTest : MockableTest {

    @MockK
    lateinit var loginRepository: LoginRepository

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            loginRepository.login(any(), any())
        }.answers {
            "123"
        }
    }

    @Test
    fun `should login`() {
       val interactor = given {
           LoginInteractor(loginRepository)
       }

        val result = whenever {
            runBlocking {
                interactor(LoginParams("1", "2"))
            }
        }

        then {
            result `should equal` "123"
        }
    }
}
