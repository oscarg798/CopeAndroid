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

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.com.cope.di.dynamicfeature.DaggerDynamicFeatureHandlerComponent
import co.com.cope.di.dynamicfeature.DynamicFeatureHandlerModule
import com.cope.core.di.CoreComponentProvider
import javax.inject.Inject

class DynamicFeaturesDeepLinkHandlerActivity : AppCompatActivity(),
    DynamicFeatureDeepLinkHandlerContract.View {

    @Inject
    lateinit var presenter: DynamicFeatureDeepLinkHandlerContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.data == null) {
            finish()
            return
        }

        DaggerDynamicFeatureHandlerComponent.builder()
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .dynamicFeatureHandlerModule(DynamicFeatureHandlerModule)
            .build()
            .inject(this)

        presenter.bind(this)

        presenter.onDeepLinkObtained(intent.data!!.toString())
    }

    override fun openDynamicFeature(dynamicFeaturePackageName: String) {
        val intent = Intent().setClassName(
            packageName,
            dynamicFeaturePackageName
        )

        startActivity(intent)
        finish()
    }
}