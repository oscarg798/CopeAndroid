package com.nequi.copedetail.domian

import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-12.
 */
@Module
class CopeModule {

    @Provides
    fun provideCopePresenter(@Named(COROUTINE_IO_CONTEXT_PROVIDER) coroutineContextProvider: CoroutineContextProvider): CopeDetailContract.Presenter {
        return CopePresenter(coroutineContextProvider)
    }
}
