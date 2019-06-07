package com.cope.login

import android.util.Log
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
            try {

                val token = withContext(coroutinesContextProvider.backgroundContext) {
                    loginInteractor(LoginParams(username, password))
                }

                Log.i("Token", token)
                view?.onLoginSuccess()
            } catch (t: Throwable) {
                view?.showError(R.string.general_error)
            }
        }

    }
}
