package com.cope.copelist

import com.cope.copelist.data.entities.APICope
import com.cope.copelist.data.entities.APICopeContent
import com.cope.copelist.data.mapper.APICopeMapper
import com.cope.core.models.Cope
import com.cope.core.models.CopeContent
import org.amshove.kluent.shouldEqual
import org.junit.Test
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class APICopeMapperTest {

    @Test
    fun `should get a cope from an APICope`() {
        val createdAt = Date()
        val updatedAt = Date()
        val apiCope = given {
            APICope("1", "2", "3", createdAt, updatedAt, listOf(APICopeContent("5","6")),"4")
        }

        val result = whenever {
            APICopeMapper.map(apiCope)
        }

        then {
            result shouldEqual Cope("1", "2", "3", createdAt, updatedAt, listOf(CopeContent("5","6")),"4")
        }
    }
}
