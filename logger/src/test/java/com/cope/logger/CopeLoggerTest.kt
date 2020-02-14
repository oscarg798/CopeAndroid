package com.cope.logger

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CopeLoggerTest {


    @MockK(relaxed = true)
    private lateinit var processor1: LoggerProcessor

    @MockK(relaxed = true)
    private lateinit var processor2: LoggerProcessor

    private val viewEvent = LogEvent.View(MyView::class.java)

    lateinit var logger: CopeLogger

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { processor1.isEventSupported(viewEvent) } answers { true }
        every { processor2.isEventSupported(viewEvent) } answers { false }

        logger = CopeLogger(listOf(processor1, processor2))
    }

    @Test
    fun `when a event arrived to the logger then all the processor that can handle it should be called`() {

        logger.log(viewEvent)

        verify {
            processor1.isEventSupported(viewEvent)
            processor2.isEventSupported(viewEvent)
            processor1.log(viewEvent)
        }

        verify(exactly = 0) {
            processor2.log(viewEvent)
        }
    }

    companion object {
        class MyView
    }
}