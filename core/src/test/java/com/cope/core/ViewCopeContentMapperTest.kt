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

package com.cope.core

import com.cope.core.mapper.ViewCopeContentMapper
import com.cope.core.models.CopeContent
import com.cope.core.models.ViewCopeContent
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class ViewCopeContentMapperTest {

    @Test
    fun `should get view cope content from cope content`() {
        val mapper = given {
            ViewCopeContentMapper
        }

        val result = whenever {
            mapper.map(CopeContent("1", "2", DateParser.getCopeDateFromBackendDateAsString("2019-06-12T16:48:13.477Z"),
                DateParser.getCopeDateFromBackendDateAsString("2019-06-12T16:48:13.477Z")))
        }

        then {
            result shouldEqual ViewCopeContent(
                "1",
                "2",
                DateParser.getCopeDateFromBackendDateAsString("2019-06-12T16:48:13.477Z"),
                DateParser.getCopeDateFromBackendDateAsString("2019-06-12T16:48:13.477Z")
            )
        }
    }
}
