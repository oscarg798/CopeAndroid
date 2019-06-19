package com.nequi.copedetail.di

import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.nequi.copedetail.CopeDetailContract
import com.nequi.copedetail.CopePresenter
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
