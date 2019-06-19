package com.nequi.copecontentdetail

import com.cope.core.CoroutineContextProvider
import com.cope.core.models.ViewCopeContent
import kotlinx.coroutines.Job

/**
 * @author Oscar Gallon on 2019-06-13.
 */
class CopeDetailPresenter(override val coroutinesContextProvider: CoroutineContextProvider) :
    CopeContentDetailContract.Presenter {

    override val parentJob: Job = Job()
    override var view: CopeContentDetailContract.View? = null

    override fun onViewCreated(copeContent: ViewCopeContent) {
        view?.showCopeContent(copeContent.text)
    }
}
