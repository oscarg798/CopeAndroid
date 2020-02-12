package com.cope.logger

import android.content.Context
import com.cope.logger.exceptions.LogEventIsNotViewTypeException
import ly.count.android.sdk.Countly
import ly.count.android.sdk.DeviceId

class CountlyLoggerProcessor(private val context: Context, private val countlyInstance: Countly) :
    LoggerProcessor {

    init {
        countlyInstance.init(
            context,
            COUNTLY_SERVER_URL,
            COUNTLY_APPI_KEY,
            null,
            DeviceId.Type.OPEN_UDID
        )

        countlyInstance.setLoggingEnabled(true)
        countlyInstance.setViewTracking(true)
    }

    override fun isEventSupported(logEvent: LogEvent): Boolean {
        return logEvent is LogEvent.View
    }

    override fun log(logEvent: LogEvent) {
        when (logEvent) {
            is LogEvent.View -> countlyInstance.recordView(logEvent.viewClass.name)
            else -> throw LogEventIsNotViewTypeException()
        }
    }

    companion object {
        const val COUNTLY_SERVER_URL = "http://94.237.29.193/"
        const val COUNTLY_APPI_KEY = "bc2b0502eebcb73dfc83325dbae53b4cd1e954e1"
    }
}