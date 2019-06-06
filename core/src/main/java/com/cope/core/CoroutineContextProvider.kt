package com.cope.core

import kotlin.coroutines.CoroutineContext

/**
 * @author Oscar Gallon on 2019-06-06.
 */
data class CoroutineContextProvider(
    val mainContext: CoroutineContext,
    val backgroundContext: CoroutineContext
)
