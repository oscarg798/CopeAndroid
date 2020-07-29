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

package com.cope.core.models.network

import com.cope.core.models.network.APICopeContent
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-11.
 */
data class APICope(
    @SerializedName("_id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("updatedAt")
    val updatedAt: Date,
    @SerializedName("content")
    val content: List<APICopeContent>,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("mainImage")
    val mainImage: String? = null
)
