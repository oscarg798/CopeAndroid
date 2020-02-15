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

package co.com.cope.deeplink.staticfeatures

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.cope.copelist.deeplink.CopeListDeepLinkModule
import com.cope.copelist.deeplink.CopeListDeepLinkModuleLoader
import com.cope.login.LoginDeepLinkModule
import com.cope.login.LoginDeepLinkModuleLoader
import com.nequi.copecontentdetail.deeplink.CopeContentDetailDeepLinkModule
import com.nequi.copecontentdetail.deeplink.CopeContentDetailDeepLinkModuleLoader
import com.nequi.copedetail.deeplink.CopeDetailDeepLinkModule
import com.nequi.copedetail.deeplink.CopeDetailDeepLinkModuleLoader
import kotlinx.coroutines.*

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@DeepLinkHandler(
    LoginDeepLinkModule::class,
    CopeListDeepLinkModule::class,
    CopeDetailDeepLinkModule::class,
    CopeContentDetailDeepLinkModule::class
)
class DeepLinkHandlerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deepLinkDelegate = DeepLinkDelegate(
            LoginDeepLinkModuleLoader(),
            CopeListDeepLinkModuleLoader(),
            CopeDetailDeepLinkModuleLoader(),
            CopeContentDetailDeepLinkModuleLoader()
        )
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}
