package com.cope.copelist.fragment

import com.cope.core.constants.StringResourceId
import com.cope.core.contract.BasePresenter
import com.cope.core.contract.BaseView
import com.cope.core.models.Cope

/**
 * @author Oscar Gallon on 2019-06-11.
 */
interface CopeListContract {

    interface View : BaseView {

        fun showCopes(copes: List<Cope>)

        fun showProgressDialog()

        fun hideProgressDialog()

        fun showError(error: StringResourceId)

        fun showError(error: String)

    }

    interface Presenter : BasePresenter<View> {

        fun onViewCreated()
    }
}
