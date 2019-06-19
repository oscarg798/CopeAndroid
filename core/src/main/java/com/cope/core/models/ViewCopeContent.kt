package com.cope.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-12.
 */
@Parcelize
data class ViewCopeContent(
    val id: String,
    val text: String,
    val createdAt: Date,
    val updatedAt: Date
) : Parcelable
