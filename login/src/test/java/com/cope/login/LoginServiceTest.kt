/*
 * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 * This file is part of Cope.
 * Cope is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cope.login

import com.cope.core.di.NetworkModule
import com.cope.login.data.entities.APiLoginParams
import com.cope.login.data.entities.UserLoginReponse
import com.cope.login.data.services.LoginService
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class LoginServiceTest : MockServerTest {

    override lateinit var mockServer: MockWebServer

    private val networkModule = NetworkModule

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
                networkModule.provideHttpClient(networkModule.provideLogginInterceptor(),  object:
                    Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        return chain.proceed(chain.request())
                    }

                })
            ).create(LoginService::class.java)
        }

        val result = whenever {
           runBlocking {
               service.login(APiLoginParams("1", "2"))
           }
        }

        then {
            result shouldEqual UserLoginReponse("123")
        }
    }
}
