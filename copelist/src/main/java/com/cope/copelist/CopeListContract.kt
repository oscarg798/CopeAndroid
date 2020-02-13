package com.cope.copelist

import com.cope.core.contract.BasePresenter
import com.cope.core.contract.BaseView

interface CopeListContract  {

    interface View: BaseView {

        fun returnToLogIn()
    }

    interface Presenter: BasePresenter<View> {

        fun onLogoutPressed()
    }
}