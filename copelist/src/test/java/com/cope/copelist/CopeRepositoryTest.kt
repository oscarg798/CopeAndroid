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
import com.cope.copelist.data.mapper.APICopeMapper
import com.cope.copelist.data.repository.CopeRepositoryImpl
import com.cope.copelist.data.service.GetCopeService
import com.cope.core.DateParser
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeRepositoryTest : MockableTest {

    @MockK
    lateinit var getCopeService: GetCopeService
    @Before
    override fun setup() {
        super.setup()

        coEvery {
            getCopeService.getCopes()
        }.answers {
            listOf(
                APICope(
                    "1", "2", "3", DateParser.getBackendDate("2019-06-12T16:48:13.477Z"), DateParser.getBackendDate("2019-06-12T16:48:13.477Z"), listOf(
                        APICopeContent("12", "13", DateParser.getBackendDate("2019-06-12T16:48:13.477Z"),
                            DateParser.getBackendDate("2019-06-12T16:48:13.477Z")),
                        APICopeContent("15", "16", DateParser.getBackendDate("2019-06-12T16:48:13.477Z"),
                            DateParser.getBackendDate("2019-06-12T16:48:13.477Z"))
                    ), "4"
                )
            )
        }
    }

    @Test
    fun `should get copes`() {
        val repository = given {
            CopeRepositoryImpl(getCopeService, APICopeMapper)
        }

        val result = whenever {
            runBlocking {
                repository.getCopes()
            }
        }

        then {
            result[0].id shouldEqual "1"
            result[0].url shouldEqual "2"
            result[0].title shouldEqual "3"
            val calendar1 = Calendar.getInstance().apply {
                result[0].createdAt
            }

            val calendar2 = Calendar.getInstance().apply {
                DateParser.getBackendDate("2019-06-12T16:48:13.477Z")
            }
            calendar1.get(Calendar.DAY_OF_MONTH) shouldEqual calendar2.get(Calendar.DAY_OF_MONTH)
            calendar1.get(Calendar.MONTH) shouldEqual calendar2.get(Calendar.MONTH)
            calendar1.get(Calendar.YEAR) shouldEqual calendar2.get(Calendar.YEAR)
            result[0].icon shouldEqual "4"
            calendar1.apply {
                result[0].updateAt
            }
            calendar1.get(Calendar.DAY_OF_MONTH) shouldEqual calendar2.get(Calendar.DAY_OF_MONTH)
            calendar1.get(Calendar.MONTH) shouldEqual calendar2.get(Calendar.MONTH)
            calendar1.get(Calendar.YEAR) shouldEqual calendar2.get(Calendar.YEAR)
        }
    }
}
