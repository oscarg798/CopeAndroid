package com.cope.core

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface DeepLinkProcessor{

    fun matches(deepLink: DeepLink): Boolean

    fun execute(deepLink: DeepLink)

    companion object {
        const val EXTRA_KEY = "deeplink_processor_extra"
    }
}
