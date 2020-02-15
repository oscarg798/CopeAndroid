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

package co.com.cope.splash

import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.Token
import com.cope.core.exceptions.DataNoFoundOnLocalStorageException
import com.cope.core.interactor.Interactor
import com.cope.core.models.None
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class SplashPresenter(
    private val getTokenInteractor: Interactor<Token, None>,
    private val initFirebaseRemoteConfigUseCase: Interactor<Unit, Unit>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : SplashContract.Presenter {

    override val parentJob: Job = Job()
    override var view: SplashContract.View? = null

    override fun bind(view: SplashContract.View) {
        super.bind(view)

        launchJobOnMainDispatchers {
            runCatching {
                withContext(coroutinesContextProvider.backgroundContext) {
                    initFirebaseRemoteConfigUseCase(Unit)
                }

                withContext(coroutinesContextProvider.backgroundContext) {
                    getTokenInteractor(None)
                }

            }.fold({
                this@SplashPresenter.view?.navigateDashboard()
            }, {
                handleException(it)

            })
        }
    }

    override fun handleException(error: Throwable) {
        super.handleException(error)

        if (error is DataNoFoundOnLocalStorageException) {
            this@SplashPresenter.view?.navigateToLogin()
        }
    }
}
