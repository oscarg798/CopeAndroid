package com.cope.signup

import android.util.Patterns
import com.cope.core.CoroutineContextProvider
import com.cope.core.interactor.Interactor
import com.cope.core.models.User
import com.cope.signup.domain.entities.SignUpParams
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

    override fun onLoginButtonClick(name: String, email: String, password: String, passwordConfirmation: String) {
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
            try {
                val user = withContext(coroutinesContextProvider.backgroundContext) {
                    signUpInteractor(SignUpParams(email, password, name))
                }

                view?.onLoginSuccess()
            } catch (t: Throwable) {
                if (t.message != null) {
                    view?.showError(t.message!!)
                } else {
                    view?.showError(R.string.general_error)
                }
            }
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isEmpty() || password.length < 6
    }
}
