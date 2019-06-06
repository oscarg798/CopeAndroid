package com.cope.login

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface MockServerTest {

    var mockServer: MockWebServer

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()

    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }
}
