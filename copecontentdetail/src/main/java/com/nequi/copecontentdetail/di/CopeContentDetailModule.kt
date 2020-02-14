package com.nequi.copecontentdetail.di

import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.nequi.copecontentdetail.CopeContentDetailContract
import com.nequi.copecontentdetail.CopeDetailPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-13.
 */
@Module
object CopeContentDetailModule {

    @Provides
    fun provideCopeDetailPresenter(
        @Named(COROUTINE_IO_CONTEXT_PROVIDER)
        coroutineContextProvider: CoroutineContextProvider
    ): CopeContentDetailContract.Presenter {
        return CopeDetailPresenter(coroutineContextProvider)
    }
}
