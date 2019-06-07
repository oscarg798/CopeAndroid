package com.cope

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cope.core.constants.LOGIN_DEEPLINK
import com.cope.core.extensions.startDeepLinkIntent
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        ivLogo?.postDelayed({
            startDeepLinkIntent(LOGIN_DEEPLINK)
        }, 3000)
    }
}
