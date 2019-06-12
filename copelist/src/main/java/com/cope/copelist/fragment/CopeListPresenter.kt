package com.cope.copelist.fragment

import com.cope.core.CoroutineContextProvider
import com.cope.core.interactor.Interactor
import com.cope.core.models.Cope
import com.cope.core.models.None
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeListPresenter(
    private val getCopeInteractor: Interactor<List<Cope>, None>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : CopeListContract.Presenter {

    override var view: CopeListContract.View? = null
    override val parentJob: Job = Job()

    override fun onViewCreated() {
        launchJobOnMainDispatchers {
            try {
                view?.showProgressDialog()
                val copes = withContext(coroutinesContextProvider.backgroundContext) {
                    getCopeInteractor(None)
                }
                view?.showCopes(copes)
            } catch (t: Throwable) {
                view?.showError(t.message ?: "Something went wrong")
            } finally {
                view?.hideProgressDialog()
            }

        }
    }
}
