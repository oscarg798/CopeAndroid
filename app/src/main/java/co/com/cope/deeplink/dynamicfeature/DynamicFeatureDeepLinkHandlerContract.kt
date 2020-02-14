package co.com.cope.deeplink.dynamicfeature

import com.cope.core.constants.DeepLink
import com.cope.core.contract.BasePresenter
import com.cope.core.contract.BaseView

interface DynamicFeatureDeepLinkHandlerContract {


    interface View : BaseView {

        fun openDynamicFeature(dynamicFeaturePackageName: String)
    }

    interface Presenter : BasePresenter<View> {

        fun onDeepLinkObtained(deepLink: DeepLink)
    }
}