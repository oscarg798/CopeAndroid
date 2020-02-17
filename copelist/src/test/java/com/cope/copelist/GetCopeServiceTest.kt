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

package com.cope.copelist

import com.cope.copelist.data.entities.APICope
import com.cope.copelist.data.entities.APICopeContent
import com.cope.copelist.data.service.GetCopeService
import com.cope.core.DateParser
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

    private val networkModule = NetworkModule

    @Before
    override fun setUp() {
        super.setUp()
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    "[\n" +
                            "    {\n" +
                            "        \"content\": [\n" +
                            "            {\n" +
                            "                \"_id\": \"5d012ccdd252d3a6f7ef1edf\",\n" +
                            "                \"text\": \"Lindo\",\n" +
                            "                \"cope\": \"5d012ccdd252d3a6f7ef1ede\",\n" +
                            "                \"createdAt\": \"2019-06-12T16:48:13.507Z\",\n" +
                            "                \"updatedAt\": \"2019-06-12T16:48:13.507Z\",\n" +
                            "                \"__v\": 0\n" +
                            "            }\n" +
                            "        ],\n" +
                            "        \"_id\": \"5d012ccdd252d3a6f7ef1ede\",\n" +
                            "        \"title\": \"RecyclerView.RecycledViewPool  |  Android Developers\",\n" +
                            "        \"url\": \"https://developer.android.com/ref22erence/android/support/v7/widget/RecyclerView.RecycledViewPool\",\n" +
                            "        \"icon\": \"nace una flor\",\n" +
                            "        \"author\": \"5d01182832ceba772528e101\",\n" +
                            "        \"createdAt\": \"2019-06-12T16:48:13.477Z\",\n" +
                            "        \"updatedAt\": \"2019-06-12T16:48:13.510Z\",\n" +
                            "        \"__v\": 1\n" +
                            "    }\n" +
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
                "5d012ccdd252d3a6f7ef1ede",
                "https://developer.android.com/ref22erence/android/support/v7/widget/RecyclerView.RecycledViewPool",
                "RecyclerView.RecycledViewPool  |  Android Developers",
                DateParser.getBackendDate("2019-06-12T16:48:13.477Z"),
                DateParser.getBackendDate("2019-06-12T16:48:13.510Z"),
                listOf(
                    APICopeContent(
                        "5d012ccdd252d3a6f7ef1edf",
                        "Lindo",
                        DateParser.getBackendDate("2019-06-12T16:48:13.507Z"),
                        DateParser.getBackendDate("2019-06-12T16:48:13.507Z")
                    )
                ),
                "nace una flor"
            )
        }
    }
}
