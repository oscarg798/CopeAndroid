package com.cope.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cope.core.StringResourceId
import com.cope.core.di.CoreComponentProvider
import com.cope.login.di.DaggerLoginComponent
import com.cope.login.di.LoginModule
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

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
            presenter.onLoginButtonPressed(etEmail.text.toString(), etPassword.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onLoginSuccess() {
        Toast.makeText(this, "Login Fine", Toast.LENGTH_LONG).show()
    }

    override fun showError(error: StringResourceId) {
        Toast.makeText(this, getString(error), Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }
}
