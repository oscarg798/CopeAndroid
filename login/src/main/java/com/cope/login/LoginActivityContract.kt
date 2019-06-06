package com.cope.login

import com.cope.core.StringResourceId
import com.cope.core.contract.BasePresenter
import com.cope.core.contract.BaseView

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface LoginActivityContract {

    interface View : BaseView {

        fun onLoginSuccess()

        fun showError(error: StringResourceId)
    }

    interface Presenter : BasePresenter<View> {

        fun onLoginButtonPressed(username: String, password: String)
    }
}
