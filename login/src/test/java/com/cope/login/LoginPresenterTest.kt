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

import android.content.Context
import android.content.SharedPreferences
import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.SharePreferenceGetter
import com.cope.core.constants.Token
import com.cope.core.interactor.Interactor
import com.cope.core.repositories.LocalStorageRepository
import com.cope.core.repositories.LocalStorageRepositoryImpl
import com.cope.login.data.LoginRepositoryImpl
import com.cope.login.data.entities.UserLoginReponse
import com.cope.login.data.services.LoginService
import com.cope.login.domain.entities.LoginParams
import com.cope.login.domain.interactor.LoginInteractor
import com.cope.login.domain.interactor.SaveTokenInteractor
import com.cope.login.domain.repositories.LoginRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okio.IOException
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import kotlin.coroutines.CoroutineContext

class LoginPresenterIntegrationTest {

    @MockK(relaxed = true)
    lateinit var context: Context

    @MockK(relaxed = true)
    lateinit var sharePreferenceEditor: SharedPreferences.Editor

    @MockK(relaxed = true)
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var localStorageRepository: LocalStorageRepository

    @MockK
    lateinit var loginService: LoginService

    private lateinit var loginRepository: LoginRepository

    private lateinit var loginInteractor: Interactor<Token, LoginParams>

    private lateinit var saveTokenInteractor: Interactor<Unit, Token>


    @MockK
    lateinit var view: LoginActivityContract.View

    private val coroutinesContextProvider =
        CoroutineContextProvider(CoroutineContextForTest, CoroutineContextForTest)

    lateinit var presenter: LoginActivityContract.Presenter

    init {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Before
    fun setup() {
        coEvery { loginService.login(any()) } answers { UserLoginReponse(MOCKED_TOKEN) }
        every { sharedPreferences.edit() } answers { sharePreferenceEditor }
        every { sharePreferenceEditor.putString(any(), any()) }.answers { sharePreferenceEditor }

        loginRepository = LoginRepositoryImpl(loginService)
        localStorageRepository = LocalStorageRepositoryImpl(context) {
            sharedPreferences
        }


        loginInteractor = spyk(LoginInteractor(loginRepository))
        saveTokenInteractor = spyk(SaveTokenInteractor(localStorageRepository))

        presenter = LoginPresenter(loginInteractor, saveTokenInteractor, coroutinesContextProvider)
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
            loginService.login(match {
                it.email == MOCKED_LOGIN_PARAMS.username && it.password == "1234567"
            })
        } answers { throw  IOException() }

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