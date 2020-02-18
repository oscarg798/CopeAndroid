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

import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import com.cope.core.CoreApplication
import com.cope.core.CoreTestApplication
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@RunWith(AndroidJUnit4::class)
@Config(application = CoreTestApplication::class)
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
