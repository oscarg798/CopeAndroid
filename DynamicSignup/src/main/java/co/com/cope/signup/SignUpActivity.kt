package co.com.cope.signup

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.com.cope.signup.di.DaggerSignUpComponent
import co.com.cope.signup.di.SignUpModule
import com.airbnb.deeplinkdispatch.DeepLink
import com.cope.core.constants.SIGNUP_DEEPLINK
import com.cope.core.constants.StringResourceId
import com.cope.core.di.CoreComponentProvider
import com.google.android.play.core.splitcompat.SplitCompat
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
        setContentView(R.layout.x)

        DaggerSignUpComponent.builder()
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .signUpModule(SignUpModule())
            .build()
            .inject(this)

        findViewById<Button>(R.id.btnSignUp).setOnClickListener {
            val name =
                findViewById<EditText>(R.id.etName).text?.toString() ?: return@setOnClickListener
            val email =
                findViewById<EditText>(R.id.etEmail)?.text?.toString() ?: return@setOnClickListener
            val password = findViewById<EditText>(R.id.etPassword)?.text?.toString()
                ?: return@setOnClickListener
            val passwordConfirmation =
                findViewById<EditText>(R.id.etPasswordConfirmation)?.text?.toString()
                    ?: return@setOnClickListener

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
