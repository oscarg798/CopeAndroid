package com.cope.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cope.R
import com.cope.core.constants.COPE_LIST_DEEP_LINK
import com.cope.core.constants.LOGIN_DEEPLINK
import com.cope.core.di.CoreComponentProvider
import com.cope.core.extensions.startDeepLinkIntent
import com.cope.di.DaggerSplashComponent
import com.cope.di.SplashModule
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        DaggerSplashComponent.builder()
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .splashModule(SplashModule())
            .build()
            .inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun navigateToLogin() {
        startDeepLinkIntent(LOGIN_DEEPLINK)
    }

    override fun navigateDashboard() {
        startDeepLinkIntent(COPE_LIST_DEEP_LINK)
    }
}