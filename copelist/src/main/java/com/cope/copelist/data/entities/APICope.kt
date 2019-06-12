package com.cope.copelist.data.entities

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-11.
 */
data class APICope(
    @SerializedName("_id") val id: String,
    val url: String,
    val title: String,
    val createdAt: Date,
    val updatedAt: Date,
    val content: List<APICopeContent>,
    val icon: String?
)
