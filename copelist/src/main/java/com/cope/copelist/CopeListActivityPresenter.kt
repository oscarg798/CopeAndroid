package com.cope.copelist

import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.constants.LOGOUT_INTERACTOR
import com.cope.core.interactor.Interactor
import com.cope.core.models.None
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import javax.inject.Named

class CopeListActivityPresenter(
    @Named(LOGOUT_INTERACTOR) private val logoutInteractor: Interactor<Unit, None>,
    @Named(COROUTINE_IO_CONTEXT_PROVIDER) override val coroutinesContextProvider: CoroutineContextProvider
) : CopeListContract.Presenter {

    override var view: CopeListContract.View? = null
    override val parentJob: Job = Job()

    override fun onLogoutPressed() {
        launchJobOnMainDispatchers {
            runCatching {
                withContext(coroutinesContextProvider.backgroundContext) {
                    logoutInteractor(None)
                }
            }.fold({
                view?.returnToLogIn()
            }, {
                throw it
            })
        }
    }

}