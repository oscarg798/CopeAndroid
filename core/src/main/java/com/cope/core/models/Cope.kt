package com.cope.core.models

import java.util.*

/**
 * @author Oscar Gallon on 2019-06-11.
 */
data class Cope(
    val id: String,
    val url: String,
    val title: String,
    val createdAt: Date,
    val updateAt: Date,
    val content: List<CopeContent>,
    val icon: String? = null
)
