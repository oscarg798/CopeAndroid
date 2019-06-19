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
                .networkModule(NetworkModule())
                .repositoryModule(RepositoryModule(this))
                .build()
        }

        return coreComponent ?: throw CoreComponentNotInitializatedException
    }
}
