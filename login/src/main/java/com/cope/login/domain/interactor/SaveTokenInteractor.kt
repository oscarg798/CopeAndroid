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

package com.cope.login.domain.interactor

import com.cope.core.constants.TOKEN
import com.cope.core.constants.Token
import com.cope.core.interactor.Interactor
import com.cope.core.repositories.LocalStorageRepository

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class SaveTokenInteractor(private val localStorageRepository: LocalStorageRepository) :
    Interactor<Unit, Token> {

    override suspend fun invoke(params: Token) {
        localStorageRepository.saveData(TOKEN, params)
    }
}
