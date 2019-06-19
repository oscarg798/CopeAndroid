package com.nequi.copecontentdetail

/**
 * @author Oscar Gallon on 2019-06-06.
 */
infix fun <T> Any.given(block: () -> T) = block.invoke()

infix fun <T> Any.whenever(block: () -> T) = block.invoke()

infix fun Any.then(block: () -> Unit) = block.invoke()
