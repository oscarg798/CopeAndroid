package com.cope.core.validators

import com.cope.core.constants.Password
import java.util.regex.Pattern

/**
 * @author Oscar Gallon on 2019-06-06.
 */
object PasswordValidator {

    fun validate(password: Password): Boolean {
        if(!Pattern.matches("^((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,})\$",password)){
            return false
        }

        return true
    }

}
