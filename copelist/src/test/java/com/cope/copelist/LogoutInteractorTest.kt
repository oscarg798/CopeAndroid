/*
 *
 *  * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 *  * This file is part of Cope.
 *  * Cope is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.cope.copelist

import com.cope.copelist.domain.LogoutInteractor
import com.cope.core.interactor.isFailure
import com.cope.core.interactor.isSuccess
import com.cope.core.models.None
import com.cope.core.repositories.LocalStorageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException

class LogoutInteractorTest {

    private val localStorageRepository: LocalStorageRepository = mockk()
    private lateinit var logoutInteractor: LogoutInteractor

    @Test
    fun `when is executed then it should return unit as Either`() {
        every { localStorageRepository.clear() } answers { Unit }

        logoutInteractor = LogoutInteractor(localStorageRepository)

        val result = runBlocking {
            logoutInteractor(None)
        }

        verify {
            result.isSuccess()
            localStorageRepository.clear()
        }
    }

    @Test
    fun `when there is and error in the localstoragerepository then it should return it as failure`() {
        every { localStorageRepository.clear() } answers { throw IOException() }

        logoutInteractor = LogoutInteractor(localStorageRepository)

        val result = runBlocking {
            logoutInteractor(None)
        }

        verify {
            result.isFailure()
            localStorageRepository.clear()
        }
    }

}