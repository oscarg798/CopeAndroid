package com.cope.copelist

import com.cope.copelist.data.entities.APICope
import com.cope.copelist.data.entities.APICopeContent
import com.cope.copelist.data.service.GetCopeService
import com.cope.copelist.domain.repository.CopeRepositoryImpl
import com.cope.core.models.Cope
import com.cope.core.models.CopeContent
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

    private val createdAt = Date()
    private val updatedAt = Date()

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            getCopeService.getCopes()
        }.answers {
            listOf(APICope("1", "2", "3", createdAt, updatedAt, listOf(APICopeContent("12", "13"),
                APICopeContent("15", "16")
            ), "4"))
        }
    }

    @Test
    fun `should get copes`() {
        val repository = given {
            CopeRepositoryImpl(getCopeService)
        }

        val result = whenever {
            runBlocking {
                repository.getCopes()
            }
        }

        then {
            result shouldEqual listOf(Cope("1", "2", "3", createdAt, updatedAt, listOf(CopeContent("12", "13"),
                CopeContent("15", "16")), "4"))
        }
    }
}
