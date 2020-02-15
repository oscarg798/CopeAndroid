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
