package com.cope.copelist

import com.cope.copelist.data.entities.APICope
import com.cope.copelist.data.mapper.APICopeMapper
import com.cope.core.models.Cope
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class APICopeMapperTest{

    @Test
    fun `should get a cope from an APICope`(){
        val apiCope = given {
            APICope("1", "2", "3", "4")
        }

        val result = whenever {
            APICopeMapper.map(apiCope)
        }

        then {
            result shouldEqual Cope("2","3","4")
        }
    }
}
