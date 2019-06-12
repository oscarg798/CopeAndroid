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
