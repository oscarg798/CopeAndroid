package com.cope

import android.content.Context
import android.content.Intent
import com.cope.core.DeepLink
import com.cope.core.DeepLinkProcessor
import com.cope.signup.SignUpActivity

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpDeepLinkProcessor(private val context: Context): DeepLinkProcessor {

    override fun execute(deepLink: DeepLink) {
       context.startActivity(Intent(context, SignUpActivity::class.java))
    }

    override fun matches(deepLink: DeepLink): Boolean {
       return  deepLink.contains("signup")
    }
}
