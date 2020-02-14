package co.com.cope.di.dynamicfeature

import co.com.cope.deeplink.dynamicfeature.DynamicFeaturesDeepLinkHandlerActivity
import com.cope.core.di.CoreComponent
import dagger.Component

@DynamicFeatureHandlerScope
@Component(
    modules = [DynamicFeatureHandlerModule::class],
    dependencies = [CoreComponent::class]
)
interface DynamicFeatureHandlerComponent {

    fun inject(dynamicFeaturesDeepLinkHandlerActivity: DynamicFeaturesDeepLinkHandlerActivity)
}