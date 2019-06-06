package com.cope.signup

import com.cope.core.StringResourceId
import com.cope.core.contract.BasePresenter
import com.cope.core.contract.BaseView

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface SignUpContract {

    interface View : BaseView {

        fun onLoginSuccess()

        fun showError(error: StringResourceId)

        fun showError(error: String)
    }

    interface Presenter : BasePresenter<View> {

        fun onLoginButtonClick(
            name: String,
            email: String,
            password: String,
            passwordConfirmation: String
        )
    }
}
