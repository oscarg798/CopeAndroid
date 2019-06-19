package com.cope.copelist.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-12.
 */
data class APICopeContent(
    @SerializedName("_id") val id: String,
    val text: String,
    val createdAt: Date,
    val updatedAt: Date
)
