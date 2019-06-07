package com.cope

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.cope.login.LoginDeepLinkModule
import com.cope.login.LoginDeepLinkModuleLoader
import com.cope.signup.SignUpDeepLinkModule
import com.cope.signup.SignUpDeepLinkModuleLoader

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@DeepLinkHandler(SignUpDeepLinkModule::class, LoginDeepLinkModule::class)
class DeepLinkHandlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deepLinkDelegate = DeepLinkDelegate(SignUpDeepLinkModuleLoader(), LoginDeepLinkModuleLoader())
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}
