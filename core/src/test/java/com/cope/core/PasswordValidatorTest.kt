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
