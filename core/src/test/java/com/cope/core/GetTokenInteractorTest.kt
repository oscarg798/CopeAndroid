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
