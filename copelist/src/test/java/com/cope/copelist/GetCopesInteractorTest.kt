package com.cope.copelist

import com.cope.copelist.domain.GetCopesInteractor
import com.cope.core.models.Cope
import com.cope.core.models.None
import com.cope.core.repositories.CopeRepository
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
class GetCopesInteractorTest : MockableTest {

    @MockK
    lateinit var copeRepository: CopeRepository

    val createdAt = Date()
    val updatedAt = Date()

    @Before
    override fun setup() {
        super.setup()

        coEvery {
            copeRepository.getCopes()
        }.answers {
            listOf(Cope("2", "3", "4", createdAt, updatedAt, listOf()))
        }
    }

    @Test
    fun `should get Copes`() {
        val interactor = given {
            GetCopesInteractor(copeRepository)
        }

        val result = whenever {
            runBlocking {
                interactor(None)
            }
        }

        then {
            result shouldEqual listOf(Cope("2", "3", "4", createdAt, updatedAt, listOf()))
        }
    }
}
