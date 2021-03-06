package com.cope.signup

import com.cope.core.di.NetworkModule
import co.cope.data.entities.APISignUpParams
import co.cope.data.entities.APIUser
import co.cope.data.service.SignupService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpServiceTest : MockServerTest {

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
                            "  \"copes\": [],\n" +
                            "  \"_id\": \"5cf9541257fb9d072a902490\",\n" +
                            "  \"uuid\": \"f68f5e23-d351-40ca-9298-412aa804fb38\",\n" +
                            "  \"name\": \"Oscar Gallon\",\n" +
                            "  \"email\": \"oscarg798@gmail.com\",\n" +
                            "  \"__v\": 0\n" +
                            "}"
                )
        )
    }

    @Test
    fun `should sing up user`() {
        val service = given {
            networkModule.provideRetrofit(
                mockServer.url(" ").toString(),
                networkModule.provideGsonConverter(),
                networkModule.provideHttpClient(networkModule.provideLogginInterceptor())
            ).create(co.cope.data.service.SignupService::class.java)
        }

        val result = whenever {
            runBlocking {
                service.signUp(co.cope.data.entities.APISignUpParams("1", "2", "3"))
            }
        }

        then {
            result `should equal` co.cope.data.entities.APIUser(
                "5cf9541257fb9d072a902490",
                "Oscar Gallon",
                "oscarg798@gmail.com"
            )
        }
    }
}
