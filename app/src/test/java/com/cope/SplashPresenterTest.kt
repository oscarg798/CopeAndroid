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

package com.cope

import arrow.core.Either
import co.com.cope.splash.SplashContract
import co.com.cope.splash.SplashPresenter
import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.Token
import com.cope.core.exceptions.DataNoFoundOnLocalStorageException
import com.cope.core.exceptions.FirebaseRemoteConfigInitializationException
import com.cope.core.interactor.Interactor
import com.cope.core.models.None
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class SplashPresenterTest {

    private val getTokenInteractor: Interactor<Either<DataNoFoundOnLocalStorageException, Token>, None> =
        mockk()
    private val initFirebaseRemoteConfigUseCase: Interactor<Either<FirebaseRemoteConfigInitializationException, Unit>, Unit> =
        mockk()
    private val coroutinesContextProvider: CoroutineContextProvider =
        CoroutineContextProvider(TestCoroutineDispatcher(), TestCoroutineDispatcher())

    private val view: SplashContract.View = mockk(relaxed = true)

    private lateinit var presenter: SplashPresenter

    @Before
    fun setup() {
        coEvery { initFirebaseRemoteConfigUseCase(any()) } answers { Either.right(Unit) }
        coEvery { getTokenInteractor(None) } answers { Either.right(TOKEN) }
        presenter = SplashPresenter(
            getTokenInteractor,
            initFirebaseRemoteConfigUseCase,
            coroutinesContextProvider
        )
    }

    @Test
    fun `when view is ready then it should init firebase remote config`() {
        presenter.bind(view)

        coVerify {
            initFirebaseRemoteConfigUseCase(any())
        }
    }

    @Test
    fun `when view is ready the it should check the token`() {
        presenter.bind(view)

        coVerify {
            getTokenInteractor(None)
        }
    }

    @Test
    fun `when there is an error init firebase then it should no go to dashboard neither login`() {
        coEvery { initFirebaseRemoteConfigUseCase(any()) } answers {
            Either.left(FirebaseRemoteConfigInitializationException())
        }

        presenter.bind(view)

        verify {
            view wasNot Called
        }
    }

    @Test
    fun `when there is an error getting the token as it was nto found then it should go to login`() {
        coEvery { getTokenInteractor(any()) } answers {
            Either.left(
                DataNoFoundOnLocalStorageException(
                    TOKEN
                )
            )
        }

        presenter.bind(view)

        verify {
            view.navigateToLogin()
        }
    }

    @Test
    fun `when there is a token then it should go to the dashboard`(){
        presenter.bind(view)

        verify {
            view.navigateDashboard()
        }
    }

    @After
    fun tearDown() {
        presenter.unBind()
    }

    companion object {
        private const val TOKEN = "123"
    }

}
