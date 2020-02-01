package com.cope.logger

sealed class LogEvent {

    class View(val viewClass: Class<*>) : LogEvent()
    class Crash(val cause: Throwable) : LogEvent()
}