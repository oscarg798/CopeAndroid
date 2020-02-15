/*
 * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 * This file is part of Cope.
 * Cope is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package co.com.cope.di.splash

import co.com.cope.splash.SplashContract
import co.com.cope.splash.SplashPresenter
import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.constants.Token
import com.cope.core.interactor.GetTokenInteractor
import com.cope.core.interactor.Interactor
import com.cope.core.models.None
import com.cope.core.repositories.LocalStorageRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-11.
 */
@Module
object SplashModule {

    @Provides
    fun provideGetTokenInteractor(localStorageRepository: LocalStorageRepository): Interactor<Token, None> {
        return GetTokenInteractor(localStorageRepository)
    }

    @Provides
    fun provideSplashPresenter(
        getTokenInteractor: Interactor<Token, None>,
        initFirebaseRemoteConfigUseCase: Interactor<Unit, Unit>,
        @Named(COROUTINE_IO_CONTEXT_PROVIDER)
        coroutineContextProvider: CoroutineContextProvider
    ): SplashContract.Presenter {
        return SplashPresenter(
            getTokenInteractor,
            initFirebaseRemoteConfigUseCase,
            coroutineContextProvider
        )
    }
}
