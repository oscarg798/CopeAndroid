package com.cope.logger

class CopeLogger(override val loggerProcessors: List<LoggerProcessor>) : Logger {

    override fun log(logEvent: LogEvent) {
       loggerProcessors.filter {
           it.isEventSupported(logEvent)
       }.forEach {
           it.log(logEvent)
       }
    }
}