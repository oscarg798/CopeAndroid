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
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(deepLink)
    arguments?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}

