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

package com.cope.copelist.domain

import arrow.core.Either
import com.cope.core.interactor.Interactor
import com.cope.core.interactor.runSafe
import com.cope.core.models.Cope
import com.cope.core.models.None
import com.cope.core.repositories.CopeRepository

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class GetCopesInteractor(private val copeRepository: CopeRepository) :
    Interactor<Either<Exception, List<Cope>>, None> {

    override suspend fun invoke(params: None): Either<Exception, List<Cope>> {
        return runSafe {
            copeRepository.getCopes()
        }
    }
}
