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

package com.cope.copelist

import arrow.core.Either
import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.constants.LOGOUT_INTERACTOR
import com.cope.core.interactor.Interactor
import com.cope.core.models.None
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import javax.inject.Named

class CopeListActivityPresenter(
    @Named(LOGOUT_INTERACTOR) private val logoutInteractor: Interactor<Either<Exception, Unit>, None>,
    @Named(COROUTINE_IO_CONTEXT_PROVIDER) override val coroutinesContextProvider: CoroutineContextProvider
) : CopeListContract.Presenter {

    override var view: CopeListContract.View? = null
    override val parentJob: Job = Job()

    override fun onLogoutPressed() {
        launchJobOnMainDispatchers {
            withContext(coroutinesContextProvider.backgroundContext) {
                logoutInteractor(None)
            }.fold({
                handleException(it)
            }, {
                view?.returnToLogIn()
            })
        }
    }
}