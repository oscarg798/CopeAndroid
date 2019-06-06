package com.cope.core.navigators

import androidx.appcompat.app.AppCompatActivity

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class LoginNavigatorImpl : LoginNavigator{


    override fun navigateOnBackPressed(from: AppCompatActivity) {
        from.finish()
    }

    override fun navigateOnLoginsuccess(from: AppCompatActivity) {
        from.finish()
    }
}
