package com.cope.login

import com.cope.core.di.NetworkModule
import com.cope.login.data.entities.APiLoginParams
import com.cope.login.data.entities.UserLoginReponse
import com.cope.login.data.services.LoginService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class LoginServiceTest : MockServerTest {

    override lateinit var mockServer: MockWebServer

    private val networkModule = NetworkModule()

    @Before
    override fun setUp() {
        super.setUp()
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    "{\n" +
                            "  \"uuid\": \"f68f5e23-d351-40ca-9298-412aa804fb38\",\n" +
                            "  \"name\": \"Oscar Gallon\",\n" +
                            "  \"email\": \"oscarg798@gmail.com\",\n" +
                            "  \"token\": \"123\"" +
                            "}"
                )
        )
    }

    @Test
    fun `should login`() {
        val service = given {
            networkModule.provideRetrofit(
                mockServer.url(" ").toString(),
                networkModule.provideGsonConverter(),
                networkModule.provideHttpClient(networkModule.provideLogginInterceptor())
            ).create(LoginService::class.java)
        }

        val result = whenever {
           runBlocking {
               service.login(APiLoginParams("1", "2"))
           }
        }

        then {
            result `should equal` UserLoginReponse("123")
        }
    }
}
