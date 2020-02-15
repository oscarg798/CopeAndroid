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

package co.com.cope.deeplink.dynamicfeature

import com.cope.core.CoroutineContextProvider
import com.cope.core.DynamicFeatureMap
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.constants.DeepLink
import com.cope.core.DynamicFeatureMappers
import com.cope.core.constants.SIGNUP_DEEPLINK
import com.cope.core.interactor.Interactor
import com.cope.core.packageName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import javax.inject.Named

class DynamicFeatureDeepLinkPresenter(
    private val dynamicFeatureMappers: DynamicFeatureMappers,
    private var dynamicModuleInstallerInteractor: Interactor<Unit, DynamicFeatureMap>,
    override val coroutinesContextProvider: CoroutineContextProvider
) : DynamicFeatureDeepLinkHandlerContract.Presenter {

    override var view: DynamicFeatureDeepLinkHandlerContract.View? = null
    override val parentJob: Job = Job()


    override fun onDeepLinkObtained(deepLink: DeepLink) {
        if (deepLink == SIGNUP_DEEPLINK) {
            getDynamicModule(dynamicFeatureMappers.signupFeatureMap)
        }
    }

    private fun getDynamicModule(moduleMapper: DynamicFeatureMap) {
        launchJobOnMainDispatchers {
            runCatching {
                withContext(Dispatchers.IO) {
                    dynamicModuleInstallerInteractor(moduleMapper)
                }
            }.fold({
                view?.openDynamicFeature(moduleMapper.packageName())
            }, {
                handleException(it)
            })
        }
    }
}