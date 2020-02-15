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

package com.cope.core

import com.cope.core.constants.BACKEND_DATE_FORMAT
import com.cope.core.constants.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-12.
 */
object DateParser {

    private val backendFormatter = SimpleDateFormat(BACKEND_DATE_FORMAT, Locale.ENGLISH)
    private val formatter = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)

    fun getCopeDateFromBackendDateAsString(candidateDate: String): Date {
        return formatter.parse(formatter.format(getBackendDate(candidateDate)))
    }

    fun getCopeDateFromBackendDate(candidateDate: Date): Date {
        return formatter.parse(formatter.format(candidateDate))
    }

    fun getBackendDate(candidateDate: String): Date {
        return backendFormatter.parse(candidateDate)
    }
}
