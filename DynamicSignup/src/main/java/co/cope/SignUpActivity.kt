package co.cope

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.com.cope.R
import co.cope.di.DaggerSignUpComponent
import com.airbnb.deeplinkdispatch.DeepLink
import com.cope.core.constants.SIGNUP_DEEPLINK
import com.cope.core.constants.StringResourceId
import com.cope.core.di.CoreComponentProvider
import co.cope.di.SignUpModule
import com.google.android.play.core.splitcompat.SplitCompat
import kotlinx.android.synthetic.main.activity_sign_up.*
import javax.inject.Inject

@DeepLink(SIGNUP_DEEPLINK)
class SignUpActivity : AppCompatActivity(), SignUpContract.View {

    @Inject
    lateinit var presenter: SignUpContract.Presenter

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }

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
