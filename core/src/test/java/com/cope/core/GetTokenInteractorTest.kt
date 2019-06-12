package com.cope.core

import com.cope.core.interactor.GetTokenInteractor
import com.cope.core.models.None
import com.cope.core.repositories.LocalStorageRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class GetTokenInteractorTest : MockableTest {

    @MockK
    lateinit var localStorageRepository: LocalStorageRepository

    @Before
    override fun setup() {
        super.setup()

        every {
            localStorageRepository.getData("token", String::class.java)
        }.answers {
            "123"
        }
    }

    @Test
    fun `should get token`() {
        val interactor = given {
            GetTokenInteractor(localStorageRepository)
        }

        val token = whenever {
            runBlocking {
                interactor(None)
            }
        }

        then {
            token shouldEqual "123"
        }
    }
}
