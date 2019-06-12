package com.cope.copelist

import com.cope.copelist.data.entities.APICope
import com.cope.copelist.data.service.GetCopeService
import com.cope.core.di.NetworkModule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class GetCopeServiceTest : MockServerTest {

    override lateinit var mockServer: MockWebServer

    private val networkModule = NetworkModule()

    @Before
    override fun setUp() {
        super.setUp()
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    "[\n" +
                            "  {\n" +
                            "    \"content\": [\n" +
                            "      {\n" +
                            "        \"_id\": \"5d001c0757fb9d072a902492\",\n" +
                            "        \"uuid\": \"cf8e7d37-4e91-40c9-9b3b-cf8334adb2e5\",\n" +
                            "        \"text\": \"nueva \",\n" +
                            "        \"cope\": \"5d001c0757fb9d072a902491\",\n" +
                            "        \"__v\": 0\n" +
                            "      }\n" +
                            "    ],\n" +
                            "    \"_id\": \"5d001c0757fb9d072a902491\",\n" +
                            "    \"uuid\": \"b898c510-7fc2-4038-8a7b-a6063e730426\",\n" +
                            "    \"title\": \"Busqueda\",\n" +
                            "    \"url\": \"www.google.com\",\n" +
                            "    \"author\": \"5cf9541257fb9d072a902490\",\n" +
                            "    \"__v\": 1\n" +
                            "  }\n" +
                            "]"
                )
        )
    }

    @Test
    fun `should get copes`() {
        val service = given {
            networkModule.provideRetrofit(
                mockServer.url(" ").toString(),
                networkModule.provideGsonConverter(),
                networkModule.provideHttpClient(networkModule.provideLogginInterceptor())
            ).create(GetCopeService::class.java)
        }

        val result = whenever {
            runBlocking {
                service.getCopes()
            }
        }

        then {
            result[0] shouldEqual APICope(
                "5d001c0757fb9d072a902491",
                "b898c510-7fc2-4038-8a7b-a6063e730426",
                "www.google.com",
                "Busqueda"
            )
        }
    }
}
