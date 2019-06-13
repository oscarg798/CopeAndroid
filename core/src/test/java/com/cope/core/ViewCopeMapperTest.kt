package com.cope.core

import com.cope.core.mapper.ViewCopeMapper
import com.cope.core.models.Cope
import com.cope.core.models.CopeContent
import com.cope.core.models.ViewCope
import com.cope.core.models.ViewCopeContent
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class ViewCopeMapperTest {

    private val createdAndUpdatedAtDate = Date()

    @Test
    fun `should get view cope from cope`() {
        val mapper = given {
            ViewCopeMapper
        }

        val result = whenever {
            mapper.map(
                Cope(
                    "1", "2", "3", createdAndUpdatedAtDate, createdAndUpdatedAtDate, listOf(
                        CopeContent("11", "12"),
                        CopeContent("13", "14")
                    ), "7"
                )
            )
        }

        then {
            result shouldEqual ViewCope(
                "1", "2", "3", createdAndUpdatedAtDate, createdAndUpdatedAtDate, listOf(
                    ViewCopeContent("11", "12"), ViewCopeContent("13", "14")
                ), "7"
            )
        }
    }
}
