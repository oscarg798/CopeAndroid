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

package com.cope.core

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.cope.core.di.*
import com.cope.core.exceptions.CoreComponentNotInitializatedException
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.firebase.FirebaseApp
import com.squareup.picasso.Picasso
import ly.count.android.sdk.Countly
import ly.count.android.sdk.DeviceId
import javax.inject.Inject

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class CoreApplication : MultiDexApplication(), CoreComponentProvider {

    private var coreComponent: CoreComponent? = null

    @Inject
    lateinit var countly: Countly

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        getCoreComponent().inject(this)

    }

    override fun getCoreComponent(): CoreComponent {
        if (coreComponent == null) {
            coreComponent = DaggerCoreComponent.builder()
                .featureFlagModule(FeatureFlagModule)
                .coreModule(CoreModule(this))
                .networkModule(NetworkModule)
                .repositoryModule(RepositoryModule(this))
                .build()
        }

        return coreComponent ?: throw CoreComponentNotInitializatedException
    }
}
