package com.cope.splash

import com.cope.core.contract.BasePresenter
import com.cope.core.contract.BaseView

/**
 * @author Oscar Gallon on 2019-06-11.
 */
interface SplashContract {

    interface View : BaseView {

        fun navigateToLogin()

        fun navigateDashboard()
    }

    interface Presenter : BasePresenter<View>
}
