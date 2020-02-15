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