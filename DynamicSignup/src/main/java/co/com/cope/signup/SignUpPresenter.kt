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

package co.com.cope.signup

import android.util.Patterns
import co.com.cope.signup.domain.entities.SignUpParams
import com.cope.core.CoroutineContextProvider
import com.cope.core.interactor.Interactor
import com.cope.core.models.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpPresenter(
    private val signUpInteractor: Interactor<User, SignUpParams>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : SignUpContract.Presenter {

    override var view: SignUpContract.View? = null
    override val parentJob: Job = Job()

    override fun onLoginButtonClick(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        if (name.isEmpty()) {
            view?.showError(R.string.name_error)
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.showError(R.string.email_error)
            return
        }

        if (!isPasswordValid(password)) {
            view?.showError(R.string.password_error)
            return
        }

        if (!isPasswordValid(passwordConfirmation)) {
            view?.showError(R.string.password_confirmation_error)
            return
        }

        if (password != passwordConfirmation) {
            view?.showError(R.string.password_do_not_match_error)
        }

        launchJobOnMainDispatchers {
            runCatching {
                withContext(coroutinesContextProvider.backgroundContext) {
                    signUpInteractor(SignUpParams(email, password, name))
                }
            }.fold({
                view?.onLoginSuccess()
            }, {

            })
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 6
    }

    override fun handleException(error: Throwable) {
        super.handleException(error)
        if (error.message != null) {
            view?.showError(error.message ?: "Something goes wrong")
        } else {
            view?.showError(R.string.general_error)
        }
    }
}
