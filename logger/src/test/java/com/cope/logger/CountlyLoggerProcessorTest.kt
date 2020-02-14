package com.cope.logger

import android.content.Context
import com.cope.logger.exceptions.LogEventIsNotViewTypeException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import ly.count.android.sdk.Countly
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test


class CountlyLoggerProcessorTest {

    @MockK(relaxed = true)
    lateinit var countly: Countly

    @MockK(relaxed = true)
    lateinit var context: Context

    private lateinit var logger: LoggerProcessor

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        logger = CountlyLoggerProcessor(context, countly)
        every { countly.recordView(MyView::class.java.name) }.answers { countly }
    }

    @Test
    fun `given a event when its from view type then it should be supported`() {
        val event = LogEvent.View(MyView::class.java)
        logger.isEventSupported(event) `should equal` true
    }

    @Test
    fun `given a view to track when the logger processor is called then it should log the view name`() {
        val event = LogEvent.View(MyView::class.java)
        logger.log(event)

        verify {
            countly.recordView(MyView::class.java.name)
        }
    }

    @Test(expected = LogEventIsNotViewTypeException::class)
    fun `given a log event that is not View Type when logger processor is called then it should throw LogEventIsNotViewTypeException`() {
        val event = LogEvent.Crash(NullPointerException())
        logger.log(event)
    }

    companion object {
        class MyView
    }
}
