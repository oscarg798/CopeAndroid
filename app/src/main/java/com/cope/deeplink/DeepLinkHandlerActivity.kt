package com.cope.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.cope.copelist.deeplink.CopeListDeepLinkModule
import com.cope.copelist.deeplink.CopeListDeepLinkModuleLoader
import com.cope.login.LoginDeepLinkModule
import com.cope.login.LoginDeepLinkModuleLoader
import com.cope.signup.SignUpDeepLinkModule
import com.cope.signup.SignUpDeepLinkModuleLoader
import com.nequi.copedetail.domian.CopeDetailDeepLinkModule
import com.nequi.copedetail.domian.CopeDetailDeepLinkModuleLoader

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@DeepLinkHandler(SignUpDeepLinkModule::class, LoginDeepLinkModule::class, CopeListDeepLinkModule::class, CopeDetailDeepLinkModule::class)
class DeepLinkHandlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deepLinkDelegate = DeepLinkDelegate(
            SignUpDeepLinkModuleLoader(),
            LoginDeepLinkModuleLoader(),
            CopeListDeepLinkModuleLoader(),
            CopeDetailDeepLinkModuleLoader()
        )
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}
