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
import com.cope.core.di.*
import com.cope.core.exceptions.CoreComponentNotInitializatedException
import com.squareup.picasso.Picasso

/**
 * @author Oscar Gallon on 2019-06-13.
 */
class CoreTestApplication : Application(), CoreComponentProvider {

    private var isPicassoInitializaded = false
    private var coreComponent: CoreComponent? = null

    override fun onCreate() {
        super.onCreate()
        if (!isPicassoInitializaded) {
            isPicassoInitializaded = true
            try {
                Picasso.setSingletonInstance(Picasso.Builder(this).build())

            } catch (e: Exception) {

            }
        }
    }

    override fun getCoreComponent(): CoreComponent {
        if (coreComponent == null) {
            coreComponent = DaggerCoreComponent.builder()
                .coreModule(CoreModule(this))
                .networkModule(NetworkModule)
                .repositoryModule(RepositoryModule(this))
                .build()
        }

        return coreComponent ?: throw CoreComponentNotInitializatedException
    }
}
