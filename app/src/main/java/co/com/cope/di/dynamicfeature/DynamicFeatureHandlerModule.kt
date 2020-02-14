package co.com.cope.di.dynamicfeature

import android.content.Context
import co.com.cope.deeplink.dynamicfeature.DynamicFeatureDeepLinkHandlerContract
import co.com.cope.deeplink.dynamicfeature.DynamicFeatureDeepLinkPresenter
import co.com.cope.domain.interactor.DynamicModuleInstallerInteractor
import com.cope.core.CoroutineContextProvider
import com.cope.core.DynamicFeatureMap
import com.cope.core.DynamicFeatureMappers
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.di.FeatureScope
import com.cope.core.interactor.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object DynamicFeatureHandlerModule {

    @Provides
    fun provideDynamicModuleInstallerInteractor(context: Context): Interactor<Unit, DynamicFeatureMap> {
        return DynamicModuleInstallerInteractor(context)
    }

    @Provides
    fun provideDynamicFeatureHandlerPresenter(
        dynamicFeatureMappers: DynamicFeatureMappers,
        interactor: Interactor<Unit, DynamicFeatureMap>,
        @Named(COROUTINE_IO_CONTEXT_PROVIDER) coroutinesContextProvider: CoroutineContextProvider
    ): DynamicFeatureDeepLinkHandlerContract.Presenter {
        return DynamicFeatureDeepLinkPresenter(
            dynamicFeatureMappers,
            interactor,
            coroutinesContextProvider
        )
    }
}