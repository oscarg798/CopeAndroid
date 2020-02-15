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

import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.Token
import com.cope.core.interactor.Interactor
import com.cope.login.domain.entities.LoginParams
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.CoroutineContext

class LoginPresenterTest {

    @MockK
    lateinit var loginInteractor: Interactor<Token, LoginParams>

    @MockK
    lateinit var saveTokenInteractor: Interactor<Unit, Token>

    @MockK
    lateinit var view: LoginActivityContract.View

    private val coroutinesContextProvider =
        CoroutineContextProvider(CoroutineContextForTest, CoroutineContextForTest)

    lateinit var presenter: LoginActivityContract.Presenter

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        presenter = LoginPresenter(loginInteractor, saveTokenInteractor, coroutinesContextProvider)
        coEvery {
            loginInteractor.invoke(match {
                it.username == MOCKED_LOGIN_PARAMS.username && it.password == MOCKED_LOGIN_PARAMS.password
            })
        } answers {
            MOCKED_TOKEN
        }
    }

    @Test
    fun `when login button is clicked but username is empty it should you the error`() {
        presenter.bind(view)
        presenter.onLoginButtonPressed("", "123123")

        verify {
            view.showError(R.string.username_empty_error)
            loginInteractor wasNot Called
            saveTokenInteractor wasNot Called
        }
    }

    @Test
    fun `when login button is clicked but password is empty it should you the error`() {
        presenter.bind(view)
        presenter.onLoginButtonPressed("123123", "")

        verify {
            view.showError(R.string.password_error)
            loginInteractor wasNot Called
            saveTokenInteractor wasNot Called
        }
    }

    @Test
    fun `when login button is clicked but password has less than 6 parameters it should you the error`() {
        presenter.bind(view)
        presenter.onLoginButtonPressed("123123", "1234")

        verify {
            view.showError(R.string.password_error)
            loginInteractor wasNot Called
            saveTokenInteractor wasNot Called
        }
    }

    @Test
    fun `when login button is clicked but username and password is empty it should you the error`() {
        presenter.bind(view)
        presenter.onLoginButtonPressed("", "")

        verify {
            view.showError(R.string.username_empty_error)
            loginInteractor wasNot Called
            saveTokenInteractor wasNot Called
        }
    }

    @Test
    fun `when login button is clicked and username and password are correct it should login`() {
        presenter.bind(view)
        presenter.onLoginButtonPressed("123", "123456")

        coVerify {
            loginInteractor.invoke(match {
                it.username == MOCKED_LOGIN_PARAMS.username && it.password == MOCKED_LOGIN_PARAMS.password
            })
            saveTokenInteractor.invoke(MOCKED_TOKEN)
            view.onLoginSuccess()
        }

        verify(exactly = 0) {
            view.showError(any())
        }
    }

    @Test
    fun `when login button is clicked and username and password are not correct it should show an error`() {
        coEvery {
            loginInteractor.invoke(match {
                it.username == "123" && it.password == "1234567"
            })
        } answers {
            throw  NullPointerException()
        }

        presenter.bind(view)
        presenter.onLoginButtonPressed("123", "1234567")


        coVerify {
            loginInteractor.invoke(match {
                it.username == MOCKED_LOGIN_PARAMS.username && it.password == "1234567"
            })
            saveTokenInteractor wasNot Called
            view.showError(R.string.general_error)
        }

        verify(exactly = 0) {
            view.onLoginSuccess()
        }
    }

    companion object {
        private const val MOCKED_TOKEN = "456"
        private val MOCKED_LOGIN_PARAMS = LoginParams("123", "123456")

        object CoroutineContextForTest : CoroutineDispatcher() {

            @ExperimentalCoroutinesApi
            override fun isDispatchNeeded(context: CoroutineContext): Boolean = false

            override fun dispatch(context: CoroutineContext, block: Runnable) {
                block.run()
            }
        }
    }
}