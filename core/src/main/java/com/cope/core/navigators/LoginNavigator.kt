package com.cope.core.navigators

import androidx.appcompat.app.AppCompatActivity

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface LoginNavigator {

    fun navigateOnLoginsuccess(from: AppCompatActivity)

    fun navigateOnBackPressed(from: AppCompatActivity)
}
