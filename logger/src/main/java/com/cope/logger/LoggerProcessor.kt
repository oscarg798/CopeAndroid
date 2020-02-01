package com.cope.logger

interface LoggerProcessor {

    fun isEventSupported(logEvent: LogEvent): Boolean
    fun log(logEvent: LogEvent)
}