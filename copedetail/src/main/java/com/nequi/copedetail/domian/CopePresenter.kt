package com.nequi.copedetail.domian

import com.cope.core.CoroutineContextProvider
import com.cope.core.models.ViewCope
import kotlinx.coroutines.Job

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class CopePresenter(override val coroutinesContextProvider: CoroutineContextProvider) : CopeDetailContract.Presenter {

    override var parentJob: Job = Job()
    override var view: CopeDetailContract.View? = null

    override fun bind(view: CopeDetailContract.View) {
        this.view = view
    }

    override fun onViewCreated(viewCope: ViewCope) {
        view?.showCopeTitle(viewCope.title)
        view?.showCopeUrl(viewCope.url)
        view?.showCopeContent(viewCope.content)
    }
}
