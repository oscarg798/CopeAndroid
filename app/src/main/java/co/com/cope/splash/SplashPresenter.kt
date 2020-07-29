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

import arrow.core.*
import arrow.core.extensions.either.applicativeError.handleError
import arrow.core.flatMap
import com.cope.core.CoroutineContextProvider
import com.cope.core.constants.Token
import com.cope.core.exceptions.DataNoFoundOnLocalStorageException
import com.cope.core.exceptions.FirebaseRemoteConfigInitializationException
import com.cope.core.interactor.Interactor
import com.cope.core.models.None
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class SplashPresenter(
    private val getTokenInteractor: Interactor<Either<DataNoFoundOnLocalStorageException, Token>, None>,
    private val initFirebaseRemoteConfigUseCase: Interactor<Either<FirebaseRemoteConfigInitializationException, Unit>, Unit>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : SplashContract.Presenter {


    override val parentJob: Job = Job()
    override var view: SplashContract.View? = null

    override fun bind(view: SplashContract.View) {
        super.bind(view)
        checkToken()
    }

    private fun checkToken() {
        launchJobOnMainDispatchers {
            withContext(coroutinesContextProvider.backgroundContext) {
                val initResult = initFirebaseRemoteConfigUseCase.invoke(Unit)

                if (initResult.isLeft()) {
                    return@withContext initResult
                }

                getTokenInteractor(None)
            }.map {
                view?.navigateDashboard()
            }.handleError {
                handleException(it)
            }
        }
    }

    override fun handleException(error: Throwable) {
        super.handleException(error)
        if (error is DataNoFoundOnLocalStorageException) {
            this@SplashPresenter.view?.navigateToLogin()
        }
    }
}