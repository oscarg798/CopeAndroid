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

import com.cope.copelist.data.entities.APICopeContent
import com.cope.copelist.data.mapper.APICopeContentMapper
import com.cope.core.DateParser
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class APICopeContentMapperTest {

    @Test
    fun `should map from api cope content`() {
        val mapper = given { APICopeContentMapper }
        val result = whenever {
            mapper.map(
                APICopeContent(
                    "1", "2", DateParser.getBackendDate("2019-06-12T16:48:13.477Z"),
                    DateParser.getBackendDate("2019-06-12T16:48:13.477Z")
                )
            )
        }

        then {
            result.id shouldEqual "1"
            result.text shouldEqual "2"
            val calendar1 = Calendar.getInstance().apply {
                time = result.createdAt
            }

            val calendar2 = Calendar.getInstance().apply {
                time = DateParser.getBackendDate("2019-06-12T16:48:13.477Z")
            }
            calendar1.get(Calendar.DAY_OF_MONTH) shouldEqual calendar2.get(Calendar.DAY_OF_MONTH)
            calendar1.get(Calendar.MONTH) shouldEqual calendar2.get(Calendar.MONTH)
            calendar1.get(Calendar.YEAR) shouldEqual calendar2.get(Calendar.YEAR)
            calendar1.apply {
                time = result.updatedAt
            }
            calendar1.get(Calendar.DAY_OF_MONTH) shouldEqual calendar2.get(Calendar.DAY_OF_MONTH)
            calendar1.get(Calendar.MONTH) shouldEqual calendar2.get(Calendar.MONTH)
            calendar1.get(Calendar.YEAR) shouldEqual calendar2.get(Calendar.YEAR)

        }
    }
}
