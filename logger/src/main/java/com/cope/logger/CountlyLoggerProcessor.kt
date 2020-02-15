/*
 * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 * This file is part of Cope.
 * Cope is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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