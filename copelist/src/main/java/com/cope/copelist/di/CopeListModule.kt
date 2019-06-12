package com.cope.copelist.di

import com.cope.copelist.fragment.CopeListContract
import com.cope.copelist.fragment.CopeListPresenter
import com.cope.copelist.data.service.GetCopeService
import com.cope.copelist.domain.GetCopesInteractor
import com.cope.copelist.domain.repository.CopeRepositoryImpl
import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.interactor.Interactor
import com.cope.core.models.Cope
import com.cope.core.models.None
import com.cope.core.repositories.CopeRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-11.
 */
@Module
class CopeListModule {

    @Provides
    fun provideGetCopeListService(retrofit: Retrofit): GetCopeService {
        return retrofit.create(GetCopeService::class.java)
    }

    @Provides
    fun provideGetCopeRepository(getCopeService: GetCopeService): CopeRepository {
        return CopeRepositoryImpl(getCopeService)
    }

    @Provides
    fun provideGetCopesInteractor(copeRepository: CopeRepository): Interactor<List<Cope>, None> {
        return GetCopesInteractor(copeRepository)
    }

    @Provides
    fun provideCopeListPresenter(
        getCopeListInteractor: Interactor<List<Cope>, None>, @Named(
            COROUTINE_IO_CONTEXT_PROVIDER
        ) coroutineContextProvider: CoroutineContextProvider
    ): CopeListContract.Presenter {
        return CopeListPresenter(getCopeListInteractor, coroutineContextProvider)
    }
}
