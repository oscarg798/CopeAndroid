package com.nequi.copedetail

import com.cope.core.CoroutineContextProvider
import com.cope.core.models.ViewCope
import com.cope.core.models.ViewCopeContent
import kotlinx.coroutines.Job

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class CopePresenter(override val coroutinesContextProvider: CoroutineContextProvider) :
    CopeDetailContract.Presenter {

    override var parentJob: Job = Job()
    override var view: CopeDetailContract.View? = null

    override fun onViewCreated(viewCope: ViewCope) {
        view?.showCopeTitle(viewCope.title)
        view?.showCopeUrl(viewCope.url)
        view?.showCopeContents(viewCope.content)
    }

    override fun onCopeConntentClickListener(copeContent: ViewCopeContent) {
        view?.showCopeContentDetail(copeContent)
    }
}
