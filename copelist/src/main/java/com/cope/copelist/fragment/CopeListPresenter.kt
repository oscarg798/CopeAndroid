package com.cope.copelist.fragment

import com.cope.core.CoroutineContextProvider
import com.cope.core.featureflags.FeatureFlagHandler
import com.cope.core.interactor.Interactor
import com.cope.core.mapper.ViewCopeMapper
import com.cope.core.models.Cope
import com.cope.core.models.None
import com.cope.logger.LogEvent
import com.cope.logger.Logger
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeListPresenter(
    private val getCopeInteractor: Interactor<List<Cope>, None>,
    private val viewCopeMapper: ViewCopeMapper,
    private val logger: Logger,
    private val featureFlagHandler: FeatureFlagHandler,
    override val coroutinesContextProvider: CoroutineContextProvider
) : CopeListContract.Presenter {

    override var view: CopeListContract.View? = null
    override val parentJob: Job = Job()

    override fun onViewCreated() {
        logger.log(LogEvent.View(CopeListFragment::class.java))
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
            runCatching {
                withContext(coroutinesContextProvider.backgroundContext) {
                    getCopeInteractor(None)
                }
            }.fold({
                view?.showCopes(it)
            }, {

            })

            view?.hideProgressDialog()
        }
    }

    override fun handleException(error: Throwable) {
        super.handleException(error)
        view?.showError(error.message ?: "Something went wrong")

    }
}
