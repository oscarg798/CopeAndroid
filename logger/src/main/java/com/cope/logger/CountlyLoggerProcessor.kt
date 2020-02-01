package com.cope.logger

import com.cope.logger.exceptions.LogEventIsNotViewTypeException
import ly.count.android.sdk.Countly

class CountlyLoggerProcessor(private val countlyInstance: Countly) : LoggerProcessor {
    override fun isEventSupported(logEvent: LogEvent): Boolean {
        return logEvent is LogEvent.View
    }

    override fun log(logEvent: LogEvent) {
        when (logEvent) {
            is LogEvent.View -> countlyInstance.recordView(logEvent.viewClass.name)
            else -> throw LogEventIsNotViewTypeException()
        }
    }
}