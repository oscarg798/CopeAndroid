package co.com.cope.deeplink.dynamicfeature

import com.cope.core.CoroutineContextProvider
import com.cope.core.DynamicFeatureMap
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.constants.DeepLink
import com.cope.core.DynamicFeatureMappers
import com.cope.core.constants.SIGNUP_DEEPLINK
import com.cope.core.interactor.Interactor
import com.cope.core.packageName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import javax.inject.Named

class DynamicFeatureDeepLinkPresenter(
    private val dynamicFeatureMappers: DynamicFeatureMappers,
    private var dynamicModuleInstallerInteractor: Interactor<Unit, DynamicFeatureMap>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : DynamicFeatureDeepLinkHandlerContract.Presenter {

    override var view: DynamicFeatureDeepLinkHandlerContract.View? = null
    override val parentJob: Job = Job()


    override fun onDeepLinkObtained(deepLink: DeepLink) {
        if (deepLink == SIGNUP_DEEPLINK) {
            getDynamicModule(dynamicFeatureMappers.signupFeatureMap)
        }
    }

    private fun getDynamicModule(moduleMapper: DynamicFeatureMap) {
        launchJobOnMainDispatchers {
            runCatching {
                withContext(Dispatchers.IO) {
                    dynamicModuleInstallerInteractor(moduleMapper)
                }
            }.fold({
                view?.openDynamicFeature(moduleMapper.packageName())
            }, {
                handleException(it)
            })
        }
    }
}