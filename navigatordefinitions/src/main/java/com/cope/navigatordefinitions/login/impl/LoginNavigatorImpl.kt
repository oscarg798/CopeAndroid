package com.cope.navigatordefinitions.login.impl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.cope.navigatordefinitions.login.LoginNavigator
import com.cope.signup.SignUpActivity

/**
 * @author Oscar Gallon on 2019-06-06.
 */
class LoginNavigatorImpl : LoginNavigator {
    override var activity: AppCompatActivity? = null

    override fun onLoginSuccess() {
        onBackPressed()
    }

    override fun onSignUpLinkClicked() {
        val wrappedActivity = activity ?: return
        wrappedActivity.startActivity(Intent(wrappedActivity, SignUpActivity::class.java))
    }

    override fun onBackPressed() {
        val wrappedActivity = activity ?: return
        wrappedActivity.finish()
    }
}
