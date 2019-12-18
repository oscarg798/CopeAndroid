package com.cope.core

import io.mockk.MockKAnnotations

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface MockableTest {

    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}
