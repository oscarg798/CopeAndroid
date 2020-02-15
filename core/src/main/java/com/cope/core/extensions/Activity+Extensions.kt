/*
 * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 * This file is part of Cope.
 * Cope is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
