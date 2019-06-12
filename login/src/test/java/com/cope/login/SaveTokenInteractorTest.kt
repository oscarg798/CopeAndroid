package com.cope.login

import com.cope.core.MockableTest
import com.cope.core.given
import com.cope.core.repositories.LocalStorageRepository
import com.cope.core.then
import com.cope.core.whenever
import com.cope.login.domain.interactor.SaveTokenInteractor
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class SaveTokenInteractorTest : MockableTest {

    @MockK
    lateinit var localStorageRepository: LocalStorageRepository

    @Before
    override fun setup() {
        super.setup()

        every {
            localStorageRepository.saveData("token","123")
        }.answers {
            Unit
        }
    }

    @Test
    fun `should save token`() {
        val interactor = given {
            SaveTokenInteractor(localStorageRepository)
        }

        whenever {
            runBlocking {
                interactor("123")
            }
        }

        then {
            verify {
                localStorageRepository.saveData("token", "123")
            }
        }
    }
}
