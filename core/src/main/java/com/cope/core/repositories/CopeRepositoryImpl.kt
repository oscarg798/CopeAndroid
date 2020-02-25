/*
 *
 *  * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 *  * This file is part of Cope.
 *  * Cope is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.cope.core.repositories

import com.cope.core.mapper.map
import com.cope.core.services.CopeService
import com.cope.core.models.Cope
import com.cope.core.models.network.APIShareCope

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeRepositoryImpl(
    private val copeService: CopeService
) : CopeRepository {

    override suspend fun getCopes(): List<Cope> {
        val apiCopes = copeService.getCopes()

        return apiCopes.map {
            it.map()
        }
    }

    override suspend fun shareCope(id: String, email: String) {
        copeService.shareCope(id, APIShareCope(email))
    }
}
