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

package com.cope.core.mapper

import com.cope.core.models.Cope
import com.cope.core.models.ViewCope
import java.net.URL

/**
 * @author Oscar Gallon on 2019-06-12.
 */
object ViewCopeMapper {

    fun map(cope: Cope): ViewCope {
        return ViewCope(
            cope.id,
            cope.url,
            getHostFromUrl(cope.url),
            cope.title,
            cope.createdAt,
            cope.updateAt,
            cope.content.map {
                ViewCopeContentMapper.map(it)
            },
            cope.icon,
            cope.mainImage
        )
    }

    private fun getHostFromUrl(url: String): String {
        return runCatching {
            val urlObject = URL(url)
            urlObject.getHost()
        }.getOrElse { url }
    }
}