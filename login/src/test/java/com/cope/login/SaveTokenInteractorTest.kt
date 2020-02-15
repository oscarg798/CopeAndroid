/*
 * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 * This file is part of Cope.
 * Cope is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cope.login

import com.cope.core.MockableTest
import com.cope.core.repositories.LocalStorageRepository
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
            localStorageRepository.saveData("token", "123")
        }.answers {
            Unit
        }
    }

    @Test
    fun `should save token`() {
        val interactor = SaveTokenInteractor(localStorageRepository)

        runBlocking {
            interactor("123")
        }

        verify {
            localStorageRepository.saveData("token", "123")
        }
    }
}
