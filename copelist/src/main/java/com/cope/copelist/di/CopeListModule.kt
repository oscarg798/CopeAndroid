package com.cope.copelist.di

import com.cope.copelist.CopeListActivityPresenter
import com.cope.copelist.data.mapper.APICopeMapper
import com.cope.copelist.data.repository.CopeRepositoryImpl
import com.cope.copelist.data.service.GetCopeService
import com.cope.copelist.domain.GetCopesInteractor
import com.cope.copelist.domain.LogoutInteractor
import com.cope.copelist.fragment.CopeListContract
import com.cope.copelist.fragment.CopeListPresenter
import com.cope.copelist.fragment.adapter.CopeViewHolder
import com.cope.copelist.fragment.adapter.viewholderfactory.CopeItem2ViewHolderFactory
import com.cope.copelist.fragment.adapter.viewholderfactory.CopeItemViewHolderFactory
import com.cope.copelist.fragment.adapter.viewholderfactory.ViewHolderFactory
import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.constants.FEATURE_FLAG_HANDLER
import com.cope.core.constants.LOGOUT_INTERACTOR
import com.cope.core.featureflags.FeatureFlagHandler
import com.cope.core.interactor.Interactor
import com.cope.core.mapper.ViewCopeMapper
import com.cope.core.models.Cope
import com.cope.core.models.None
import com.cope.core.repositories.CopeRepository
import com.cope.core.repositories.LocalStorageRepository
import com.cope.logger.Logger
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-11.
 */
@Module
object CopeListModule {

    @Provides
    fun provideGetCopeListService(retrofit: Retrofit): GetCopeService {
        return retrofit.create(GetCopeService::class.java)
    }

    @Provides
    fun provideGetCopeRepository(getCopeService: GetCopeService): CopeRepository {
        return CopeRepositoryImpl(getCopeService, APICopeMapper)
    }

    @Provides
    fun provideGetCopesInteractor(copeRepository: CopeRepository): Interactor<List<Cope>, None> {
        return GetCopesInteractor(copeRepository)
    }

    @Provides
    fun provideViewHolderFactory(@Named(FEATURE_FLAG_HANDLER) featureFlagHandler: FeatureFlagHandler): List<@JvmSuppressWildcards ViewHolderFactory<@JvmSuppressWildcards CopeViewHolder>> {
        return listOf(
            CopeItem2ViewHolderFactory(featureFlagHandler),
            CopeItemViewHolderFactory(featureFlagHandler)
        )
    }


    @Named(LOGOUT_INTERACTOR)
    @Provides
    fun provideLogoutInteractor(localStorageRepository: LocalStorageRepository): Interactor<Unit, None> {
        return LogoutInteractor(localStorageRepository)
    }

    @Provides
    fun provideCopeListActivityPresenter(
        @Named(LOGOUT_INTERACTOR) logoutInteractor: Interactor<Unit, None>,
        @Named(
            COROUTINE_IO_CONTEXT_PROVIDER
        ) coroutineContextProvider: CoroutineContextProvider
    ): com.cope.copelist.CopeListContract.Presenter {
        return CopeListActivityPresenter(logoutInteractor, coroutineContextProvider)
    }


    @Provides
    fun provideCopeListPresenter(
        getCopeListInteractor: Interactor<List<Cope>, None>,
        @Named(FEATURE_FLAG_HANDLER) featureFlagHandler: FeatureFlagHandler,
        logger: Logger,
        @Named(
            COROUTINE_IO_CONTEXT_PROVIDER
        ) coroutineContextProvider: CoroutineContextProvider
    ): CopeListContract.Presenter {
        return CopeListPresenter(
            getCopeListInteractor,
            ViewCopeMapper,
            logger,
            featureFlagHandler,
            coroutineContextProvider
        )
    }
}
