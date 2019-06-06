package com.cope.login

import io.mockk.MockKAnnotations
import org.junit.Before

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface MockableTest{

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}
