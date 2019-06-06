package com.cope.signup

import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cope.core.CoreApplication
import com.google.android.material.textfield.TextInputLayout
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@RunWith(AndroidJUnit4::class)
@Config(application = CoreApplication::class)
class SignUpActivityTest {

    @Test
    fun `should have name text field`() {
        ActivityScenario.launch(SignUpActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.etName)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `should have email text field`() {
        ActivityScenario.launch(SignUpActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `should have password text field`() {
        ActivityScenario.launch(SignUpActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `should have password confirmation text field`() {
        ActivityScenario.launch(SignUpActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.etPasswordConfirmation))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `should have sign up button`() {
        ActivityScenario.launch(SignUpActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.btnSignUp)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `btn sign up should have proper text`() {
        ActivityScenario.launch(SignUpActivity::class.java)
        Espresso.onView(withText(R.string.btn_sign_up_text)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `email text field should have proper hint`() {
        ActivityScenario.launch(SignUpActivity::class.java).onActivity {
            val etEmail = it.findViewById<TextInputLayout>(R.id.tilEmail)
            etEmail.hint shouldEqual it.getString(R.string.email_hint)
        }

    }

    @Test
    fun `password text field should have proper hint`() {
        ActivityScenario.launch(SignUpActivity::class.java).onActivity {
            val etEmail = it.findViewById<TextInputLayout>(R.id.tilPassword)
            etEmail.hint shouldEqual it.getString(R.string.et_password_hint)
        }
    }

    @Test
    fun `password confirmation text field should have proper hint`() {
        ActivityScenario.launch(SignUpActivity::class.java).onActivity {
            val etEmail = it.findViewById<TextInputLayout>(R.id.tilPasswordConfirmation)
            etEmail.hint shouldEqual it.getString(R.string.et_password_confirmation_hint)
        }
    }

    @Test
    fun `name text field should have proper hint`() {
        ActivityScenario.launch(SignUpActivity::class.java).onActivity {
            val etEmail = it.findViewById<TextInputLayout>(R.id.tilName)
            etEmail.hint shouldEqual it.getString(R.string.et_name_hint)
        }
    }
}
