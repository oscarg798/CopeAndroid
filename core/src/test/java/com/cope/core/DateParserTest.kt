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

package com.cope.core

import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.text.SimpleDateFormat

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class DateParserTest {

    private val dateFormat = SimpleDateFormat("MM/dd/yyyy")

    @Test
    fun `should parse date`() {
        val parser = given {
            DateParser
        }

        val result = whenever {
            parser.getCopeDateFromBackendDateAsString("2019-06-12T16:48:13.510Z")
        }

        then {
            val assertionResult = dateFormat.parse("06/12/2019")
            result.day shouldEqual assertionResult.day
            result.month shouldEqual assertionResult.month
            result.year shouldEqual assertionResult.year
        }
    }

    @Test
    fun `should get parse date from date`() {
        val parser = given {
            DateParser
        }

        val result = whenever {
            val backendDate = parser.getBackendDate("2019-06-12T16:48:13.510Z")
            parser.getCopeDateFromBackendDate(backendDate)
        }

        then {
            val assertionResult = dateFormat.parse("06/12/2019")
            result.day shouldEqual assertionResult.day
            result.month shouldEqual assertionResult.month
            result.year shouldEqual assertionResult.year
        }
    }
}
