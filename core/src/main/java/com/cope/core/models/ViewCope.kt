package com.cope.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-12.
 */
@Parcelize
data class ViewCope(
    val id: String,
    val url: String,
    val title: String,
    val createdAt: Date,
    val updateAt: Date,
    val content: List<ViewCopeContent>,
    val icon: String? = null
) : Parcelable
