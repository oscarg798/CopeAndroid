package com.cope.copelist

import com.cope.copelist.data.entities.APICope
import com.cope.copelist.data.service.GetCopeService
import com.cope.copelist.domain.repository.CopeRepositoryImpl
import com.cope.core.models.Cope
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

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
            listOf(APICope("1", "2", "3", "4"))
        }
    }

    @Test
    fun `should get copes`(){
        val repository = given {
            CopeRepositoryImpl(getCopeService)
        }

        val  result = whenever {
            runBlocking {
                repository.getCopes()
            }
        }

        then {
            result shouldEqual listOf(Cope("2","3","4"))
        }
    }
}
