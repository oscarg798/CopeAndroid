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

package com.cope.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.cope.core.constants.COPE_LIST_DEEP_LINK
import com.cope.core.constants.LOGIN_DEEPLINK
import com.cope.core.constants.SIGNUP_DEEPLINK
import com.cope.core.constants.StringResourceId
import com.cope.core.di.CoreComponentProvider
import com.cope.core.extensions.startDeepLinkIntent
import com.cope.login.di.DaggerLoginComponent
import com.cope.login.di.LoginModule
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

@DeepLink(LOGIN_DEEPLINK)
class LoginActivity : AppCompatActivity(), LoginActivityContract.View {

    @Inject
    lateinit var presenter: LoginActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        DaggerLoginComponent.builder()
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .loginModule(LoginModule())
            .build()
            .inject(this)

        btnLogin?.setOnClickListener {
            val email = etEmail?.text?.toString() ?: return@setOnClickListener
            val password = etPassword?.text?.toString() ?: return@setOnClickListener

            presenter.onLoginButtonPressed(email, password)
        }
        
        tvSignUp?.setOnClickListener {
            startDeepLinkIntent(SIGNUP_DEEPLINK)
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun onLoginSuccess() {
        startDeepLinkIntent(COPE_LIST_DEEP_LINK)
    }

    override fun showError(error: StringResourceId) {
        Toast.makeText(this, getString(error), Toast.LENGTH_LONG).show()
    }
}
