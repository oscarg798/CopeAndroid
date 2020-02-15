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
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class LoginPresenter(
    private val loginInteractor: Interactor<Token, LoginParams>,
    private val saveTokenInteractor: Interactor<Unit, Token>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : LoginActivityContract.Presenter {

    override var view: LoginActivityContract.View? = null

    override val parentJob: Job = Job()

    override fun onLoginButtonPressed(username: String, password: String) {
        if (username.isEmpty()) {
            view?.showError(R.string.username_empty_error)
            return
        }

        if (password.isEmpty() || password.length < 6) {
            view?.showError(R.string.password_error)
            return
        }

        launchJobOnMainDispatchers {
            runCatching {
                withContext(coroutinesContextProvider.backgroundContext) {
                    val token = loginInteractor(LoginParams(username, password))
                    saveTokenInteractor(token)
                }
            }.fold({
                view?.onLoginSuccess()

            },{
                handleException(it)
            })
        }
    }

    override fun handleException(error: Throwable) {
        super.handleException(error)
        view?.showError(R.string.general_error)
    }
}
