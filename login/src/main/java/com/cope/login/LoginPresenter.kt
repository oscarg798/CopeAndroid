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
