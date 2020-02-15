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
