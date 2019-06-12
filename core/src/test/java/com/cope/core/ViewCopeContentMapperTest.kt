package com.cope.core

import com.cope.core.mapper.ViewCopeContentMapper
import com.cope.core.models.CopeContent
import com.cope.core.models.ViewCopeContent
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class ViewCopeContentMapperTest  {

    @Test
    fun `should get view cope content from cope content`(){
        val mapper = given {
            ViewCopeContentMapper
        }

        val result = whenever {
            mapper.map(CopeContent("1","2"))
        }

        then {
            result shouldEqual ViewCopeContent("1","2")
        }
    }
}
