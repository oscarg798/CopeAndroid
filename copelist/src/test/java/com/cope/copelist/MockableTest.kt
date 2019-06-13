package com.cope.copelist

import io.mockk.MockKAnnotations
import org.junit.Before

/**
 * @author Oscar Gallon on 2019-06-11.
 */
interface MockableTest {

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}
