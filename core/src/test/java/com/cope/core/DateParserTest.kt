package com.cope.core

import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.text.SimpleDateFormat

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class DateParserTest {


    private val dateFormat = SimpleDateFormat("mm/dd/yyyy")

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
