package com.cope.copelist.data.entities

import com.google.gson.annotations.SerializedName

/**
 * @author Oscar Gallon on 2019-06-11.
 */
data class APICope(
    @SerializedName("_id") val id: String,
    val uuid: String,
    val url: String,
    val title: String
)
