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
