package co.com.cope.splash

import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.Token
import com.cope.core.exceptions.DataNoFoundOnLocalStorageException
import com.cope.core.interactor.Interactor
import com.cope.core.models.None
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class SplashPresenter(
    private val getTokenInteractor: Interactor<Token, None>,
    private val initFirebaseRemoteConfigUseCase: Interactor<Unit, Unit>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : SplashContract.Presenter {

    override val parentJob: Job = Job()
    override var view: SplashContract.View? = null

    override fun bind(view: SplashContract.View) {
        super.bind(view)

        launchJobOnMainDispatchers {
            runCatching {
                withContext(coroutinesContextProvider.backgroundContext) {
                    initFirebaseRemoteConfigUseCase(Unit)
                }

                withContext(coroutinesContextProvider.backgroundContext) {
                    getTokenInteractor(None)
                }

            }.fold({
                this@SplashPresenter.view?.navigateDashboard()
            }, {
                handleException(it)

            })
        }
    }

    override fun handleException(error: Throwable) {
        super.handleException(error)

        if (error is DataNoFoundOnLocalStorageException) {
            this@SplashPresenter.view?.navigateToLogin()
        }
    }
}
