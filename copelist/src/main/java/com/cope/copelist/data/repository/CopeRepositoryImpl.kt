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

package com.cope.copelist.data.repository

import com.cope.copelist.data.mapper.APICopeMapper
import com.cope.copelist.data.service.GetCopeService
import com.cope.core.models.Cope
import com.cope.core.repositories.CopeRepository

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeRepositoryImpl(
    private val getCopeService: GetCopeService,
    private val apiCopeMapper: APICopeMapper
) : CopeRepository {

    override suspend fun getCopes(): List<Cope> {
        val apiCopes = getCopeService.getCopes()

        return apiCopes.map {
            apiCopeMapper.map(it)
        }
    }
}
