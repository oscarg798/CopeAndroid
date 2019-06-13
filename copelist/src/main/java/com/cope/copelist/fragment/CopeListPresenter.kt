package com.cope.copelist.fragment

import com.cope.core.CoroutineContextProvider
import com.cope.core.interactor.Interactor
import com.cope.core.mapper.ViewCopeMapper
import com.cope.core.models.Cope
import com.cope.core.models.None
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeListPresenter(
    private val getCopeInteractor: Interactor<List<Cope>, None>,
    private val viewCopeMapper: ViewCopeMapper,
    override val coroutinesContextProvider: CoroutineContextProvider
) : CopeListContract.Presenter {

    override var view: CopeListContract.View? = null
    override val parentJob: Job = Job()

    override fun onViewCreated() {
        getCopes()
    }

    override fun onRefresh() {
        if (parentJob.children.filter { it.isActive }.count() > 0) {
            view?.hideProgressDialog()
            return
        }

        onViewCreated()
    }

    override fun onCopeClick(cope: Cope) {
        launchJobOnMainDispatchers {
            val viewCope = withContext(coroutinesContextProvider.backgroundContext) {
                viewCopeMapper.map(cope)
            }

            view?.openCopeDetails(viewCope)
        }
    }

    private fun getCopes() {
        view?.showProgressDialog()
        launchJobOnMainDispatchers {
            try {
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
