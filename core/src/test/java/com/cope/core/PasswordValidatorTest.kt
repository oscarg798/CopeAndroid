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

import com.cope.core.validators.PasswordValidator
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class PasswordValidatorTest {

    @Test
    fun `should be invalid with empty password`() {
        PasswordValidator.validate("") shouldEqual false
    }

    @Test
    fun `should be invalid if password is less than six characters`() {
        PasswordValidator.validate("1234")
    }

    @Test
    fun `should be invalid if no constaint digits and letters`() {
        PasswordValidator.validate("123456") shouldEqual false
    }

    @Test
    fun `should be invalid if not contains an uppercase letter`() {
        PasswordValidator.validate("123456a") shouldEqual false
    }
}
