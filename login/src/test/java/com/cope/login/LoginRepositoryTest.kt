package com.cope.login

import com.cope.login.data.LoginRepositoryImpl
import com.cope.login.data.services.LoginService
import com.cope.login.data.entities.APiLoginParams
import com.cope.login.data.entities.UserLoginReponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class LoginRepositoryTest : MockableTest {

    @MockK
    lateinit var loginService: LoginService

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            loginService.login(any())
        }.answers {
            UserLoginReponse("123")
        }

        coEvery {
            loginService.login(APiLoginParams("1", "3"))
        }.answers {
           throw NullPointerException()
        }
    }

    @Test
    fun `should login success`() {
        val repository = given {
            LoginRepositoryImpl(loginService)
        }

        val result = whenever {
            runBlocking {
                repository.login("1", "2")
            }
        }

        then {
            result `should equal` "123"
            coVerify {
                loginService.login(any())
            }
        }
    }

    @Test(expected = NullPointerException::class)
    fun `should throw exception`() {
        val repository = given {
            LoginRepositoryImpl(loginService)
        }

        val result = whenever {
            runBlocking {
                repository.login("1", "3")
            }
        }

        then {
            coVerify {
                loginService.login(any())
            }
        }
    }
}
