/*
 *
 *  * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 *  * This file is part of Cope.
 *  * Cope is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package co.com.sharedialog.di

import android.util.Patterns
import co.com.sharedialog.ShareDialogContract
import co.com.sharedialog.ShareDialogPresenter
import co.com.sharedialog.entities.ShareCopeParams
import co.com.sharedialog.interactor.ShareCopeInteractor
import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.interactor.Interactor
import com.cope.core.repositories.CopeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object ShareDialogModule {

    @Provides
    fun provideShareCopeInteractor(copeRepository: CopeRepository): Interactor<Unit, ShareCopeParams> {
        return ShareCopeInteractor(copeRepository)
    }

    @Provides
    fun provideShareCopeDialogPresenter(
        interactor: Interactor<Unit, ShareCopeParams>, @Named(
            COROUTINE_IO_CONTEXT_PROVIDER
        ) coroutineContextProvider: CoroutineContextProvider
    ): ShareDialogContract.Presenter {
        return ShareDialogPresenter({
            Patterns.EMAIL_ADDRESS.matcher(it).matches()
        }, interactor, coroutineContextProvider)
    }
}