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

import arrow.core.Either
import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.constants.LOGOUT_INTERACTOR
import com.cope.core.interactor.Interactor
import com.cope.core.models.None
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Named

@ExperimentalCoroutinesApi
class CopeListPresenterTest {

    private val logoutInteractor: Interactor<Either<Exception, Unit>, None> = mockk()
    private val coroutinesContextProvider: CoroutineContextProvider =
        CoroutineContextProvider(TestCoroutineDispatcher(), TestCoroutineDispatcher())
    private val view: CopeListContract.View = mockk(relaxed = true)

    private lateinit var presenter: CopeListActivityPresenter

    @Before
    fun setup() {
        coEvery { logoutInteractor.invoke(None) } answers { Either.right(Unit) }
        presenter = CopeListActivityPresenter(logoutInteractor, coroutinesContextProvider)
    }

    @Test
    fun `when logout is pressed then the logout interactor should be called`() {
        presenter.bind(view)

        presenter.onLogoutPressed()

        coVerify {
            logoutInteractor.invoke(None)
        }
    }

    @Test
    fun `when logoutinteractor run successfull then it should go to login`() {
        presenter.bind(view)

        presenter.onLogoutPressed()

        coVerify {
            logoutInteractor(None)
            view.returnToLogIn()
        }
    }

    @Test
    fun `when there is an error in the logoutinteractor then it should not go to login`(){
        coEvery { logoutInteractor.invoke(None) } answers { Either.left(Exception()) }
        presenter.bind(view)

        presenter.onLogoutPressed()

        coVerify {
            logoutInteractor(None)
            view wasNot Called
        }
    }

    @After
    fun tearDown() {
        presenter.unBind()
    }
}