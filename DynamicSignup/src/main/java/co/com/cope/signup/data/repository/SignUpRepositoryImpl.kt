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

package co.com.cope.signup.data.repository

import co.com.cope.signup.data.entities.APISignUpParams
import co.com.cope.signup.data.mapper.UserMapper
import co.com.cope.signup.data.service.SignupService
import co.com.cope.signup.domain.entities.SignUpParams
import co.com.cope.signup.domain.repositories.SignUpRepository
import com.cope.core.models.User

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class SignUpRepositoryImpl(private val signUpService: SignupService) :
    SignUpRepository {

    override suspend fun signUp(params: SignUpParams): User {
        val apiUser = signUpService.signUp(
            APISignUpParams(
                params.email,
                params.password,
                params.name
            )
        )
        return UserMapper.map(apiUser)
    }
}
