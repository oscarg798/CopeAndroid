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

package com.cope.login.data.services

import com.cope.login.data.entities.APiLoginParams
import com.cope.login.data.entities.UserLoginReponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface LoginService {

    @POST("auth")
    suspend fun login(@Body aPiLoginParams: APiLoginParams): UserLoginReponse
}
