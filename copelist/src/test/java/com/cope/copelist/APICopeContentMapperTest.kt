package com.cope.copelist

import com.cope.copelist.data.entities.APICopeContent
import com.cope.copelist.data.mapper.APICopeContentMapper
import com.cope.core.models.CopeContent
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class APICopeContentMapperTest {

    @Test
    fun `should map from api cope content`() {
        val mapper = given { APICopeContentMapper }
        val result = whenever {
            mapper.map(APICopeContent("1","2"))
        }

        then{
            result shouldEqual  CopeContent("1","2")
        }
    }
}
