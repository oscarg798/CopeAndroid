package com.nequi.copecontentdetail

import com.cope.core.contract.BasePresenter
import com.cope.core.contract.BaseView
import com.cope.core.models.ViewCopeContent

/**
 * @author Oscar Gallon on 2019-06-13.
 */
interface CopeContentDetailContract {

    interface View : BaseView {

        fun showCopeContent(content: String)
    }

    interface Presenter : BasePresenter<View> {

        fun onViewCreated(copeContent: ViewCopeContent)
    }
}
