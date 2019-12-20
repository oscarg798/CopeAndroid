package com.cope.core

import android.app.Application
import com.cope.core.di.*
import com.cope.core.exceptions.CoreComponentNotInitializatedException
import com.squareup.picasso.Picasso

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class CoreApplication : Application(), CoreComponentProvider {

    private var coreComponent: CoreComponent? = null

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
