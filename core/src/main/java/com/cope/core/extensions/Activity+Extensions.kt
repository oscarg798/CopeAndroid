package com.cope.core.extensions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cope.core.constants.DeepLink

/**
 * @author Oscar Gallon on 2019-06-06.
 */
fun AppCompatActivity.startDeepLinkIntent(deepLink: DeepLink, arguments: Bundle? = null) {
    val intent = createDeepLinkIntent(deepLink)
    startIntent(arguments, intent)
}

fun AppCompatActivity.startDeepLinkIntent(
    deepLink: DeepLink,
    arguments: Bundle? = null,
    flags: Int
) {
    val intent = createDeepLinkIntent(deepLink)
    intent.flags = flags
    startIntent(arguments, intent)
}

private fun createDeepLinkIntent(deepLink: DeepLink): Intent {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(deepLink)
    return intent
}

private fun AppCompatActivity.startIntent(
    arguments: Bundle?,
    intent: Intent
) {
    arguments?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}
