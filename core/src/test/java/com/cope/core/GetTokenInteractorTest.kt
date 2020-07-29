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

import arrow.core.orNull
import com.cope.core.constants.Token
import com.cope.core.exceptions.DataNoFoundOnLocalStorageException
import com.cope.core.interactor.GetTokenInteractor
import com.cope.core.interactor.isFailure
import com.cope.core.interactor.isSuccess
import com.cope.core.models.None
import com.cope.core.repositories.LocalStorageRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class GetTokenInteractorTest : MockableTest {

    @MockK
    lateinit var localStorageRepository: LocalStorageRepository

    lateinit var interactor: GetTokenInteractor

    @Before
    override fun setup() {
        super.setup()
        every {
            localStorageRepository.getData("token", String::class.java)
        }.answers {
            "123"
        }

        interactor = GetTokenInteractor(localStorageRepository)
    }

    @Test
    fun `when usecase is executed then it should get the token as either`() {
        val result = runBlocking {
            interactor(None)
        }

        result.isSuccess() shouldBe true
        result.orNull() shouldEqual "123"

    }

    @Test
    fun `when there is a DataNoFoundOnLocalStorageException getting the token then it should be returned as either`() {
        every {
            localStorageRepository.getData(
                "token",
                String::class.java
            )
        } answers { throw DataNoFoundOnLocalStorageException("token") }

        val result = runBlocking {
            interactor(None)
        }

        result.isFailure() shouldBe true
    }

    @Test(expected = IOException::class)
    fun `if there is any error not excepted getting the token then it should crash`() {
        every {
            localStorageRepository.getData(
                "token",
                String::class.java
            )
        } answers { throw IOException() }

        runBlocking {
            interactor(None)
        }
    }
}
