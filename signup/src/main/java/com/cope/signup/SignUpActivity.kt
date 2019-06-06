package com.cope.signup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cope.core.StringResourceId
import com.cope.core.di.CoreComponentProvider
import com.cope.signup.di.DaggerSignUpComponent
import com.cope.signup.di.SignUpModule
import kotlinx.android.synthetic.main.activity_sign_up.*
import javax.inject.Inject

class SignUpActivity : AppCompatActivity(), SignUpContract.View {

    @Inject
    lateinit var presenter: SignUpContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        DaggerSignUpComponent.builder()
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .signUpModule(SignUpModule())
            .build()
            .inject(this)

        btnSignUp?.setOnClickListener {
            val name = etName?.text?.toString() ?: return@setOnClickListener
            val email = etEmail?.text?.toString() ?: return@setOnClickListener
            val password = etPassword?.text?.toString() ?: return@setOnClickListener
            val passwordConfirmation = etPasswordConfirmation?.text?.toString() ?: return@setOnClickListener

            presenter.onLoginButtonClick(name, email, password, passwordConfirmation)
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
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
    }

    override fun showError(error: StringResourceId) {
        showError(getString(error))
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
