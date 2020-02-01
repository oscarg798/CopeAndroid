package com.cope.logger

interface Logger {

    val loggerProcessors: List<LoggerProcessor>

    fun log(logEvent: LogEvent)
}