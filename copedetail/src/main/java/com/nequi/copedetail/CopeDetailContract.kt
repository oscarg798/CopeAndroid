package com.nequi.copedetail

import com.cope.core.contract.BasePresenter
import com.cope.core.contract.BaseView
import com.cope.core.models.ViewCope
import com.cope.core.models.ViewCopeContent

/**
 * @author Oscar Gallon on 2019-06-12.
 */
interface CopeDetailContract {

    interface View : BaseView {

        fun showCopeTitle(title: String)

        fun showCopeUrl(url: String)

        fun showCopeContents(viewCopeContents: List<ViewCopeContent>)

        fun showCopeContentDetail(viewCopeContent: ViewCopeContent)
    }

    interface Presenter : BasePresenter<View>, CopeContentClickListener {

        fun onViewCreated(viewCope: ViewCope)
    }
}
