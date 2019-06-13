package com.cope.login

import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cope.core.CoreApplication
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@RunWith(AndroidJUnit4::class)
@Config(application = CoreApplication::class)
class LoginActivityTest {

    @Test
    fun `should have email text field`() {
        ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.etEmail)).check(matches(isDisplayed()))
    }

    @Test
    fun `should have password text field`() {
        ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.etPassword)).check(matches(isDisplayed()))
    }

    @Test
    fun `should have login button`() {
        ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))
    }

    @Test
    fun `login button should have proper text`() {
        ActivityScenario.launch(LoginActivity::class.java)
        onView(withText(R.string.btn_login_text)).check(matches(isDisplayed()))
    }

    @Test
    fun `should show signup link`() {
        val activity = ActivityScenario.launch(LoginActivity::class.java)

        activity.moveToState(Lifecycle.State.RESUMED)

        activity.onActivity {
            it.findViewById<TextView>(R.id.tvSignUp).text shouldEqual it.getString(R.string.sign_up_link_text)
        }
    }
}
