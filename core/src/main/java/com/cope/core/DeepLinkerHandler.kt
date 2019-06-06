package com.cope.core

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface DeepLinkerHandler {

    fun process(deepLink: String): Boolean
}
