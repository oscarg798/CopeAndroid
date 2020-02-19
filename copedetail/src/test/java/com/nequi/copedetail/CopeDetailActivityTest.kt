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

package com.nequi.copedetail

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.com.cope.CoreApplication
import com.cope.core.CoreTestApplication
import com.cope.core.constants.ARGUMENT_COPE
import com.cope.core.models.ViewCope
import com.cope.core.models.ViewCopeContent
import org.amshove.kluent.shouldEqual
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-12.
 */
@RunWith(AndroidJUnit4::class)
@Config(application = CoreTestApplication::class)
class CopeDetailActivityTest {

    @get:Rule
    val rule = object : ActivityTestRule<CopeDetailActivity>(CopeDetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return Intent().apply {
                putExtra(ARGUMENT_COPE, ViewCope("1", "www.google.com", "Google", Date(), Date(), listOf()))
            }
        }
    }

    private val dateFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)


    @Test
    fun `should show the cope title`() {
        rule.activity.findViewById<TextView>(R.id.tvCopeTitle).visibility shouldEqual View.VISIBLE
    }

    @Test
    fun `should show the cope url`() {
        rule.activity.findViewById<TextView>(R.id.tvCopeUrl).visibility shouldEqual View.VISIBLE
    }

    @Test
    fun `should have a recycler view for the content`() {
        rule.activity.findViewById<RecyclerView>(R.id.rvCopeContent).visibility shouldEqual View.VISIBLE
    }

    @Test
    fun `should display cope content text`() {
        val activity = rule.activity

        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.rvCopeContent)
        (recyclerView.adapter as CopeDetailAdapter).add(
            listOf(
                ViewCopeContent("1", "This is a content that was search", dateFormatter.parse("Jun 12, 2019"),
                    dateFormatter.parse("Jun 13, 2019"))
            )
        )

        Espresso.onView(nthChildOf(ViewMatchers.withId(R.id.rvCopeContent), 0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("This is a content that was search"))))
    }

    @Test
    fun `should display item divider`() {
        val activity = rule.activity

        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.rvCopeContent)
        (recyclerView.adapter as CopeDetailAdapter).add(
            listOf(
                ViewCopeContent("1", "This is a content that was search", dateFormatter.parse("Jun 12, 2019"),
                    dateFormatter.parse("Jun 13, 2019"))
            )
        )

        Espresso.onView(nthChildOf(ViewMatchers.withId(R.id.rvCopeContent), 0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withId(R.id.ivDivider))))
    }

    @Test
    fun `should have a view to display content created at`() {
        val activity = rule.activity

        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.rvCopeContent)
        (recyclerView.adapter as CopeDetailAdapter).add(
            listOf(
                ViewCopeContent("1", "This is a content that was search", dateFormatter.parse("Jun 12, 2019"),
                    dateFormatter.parse("Jun 13, 2019") )
            )
        )

        Espresso.onView(nthChildOf(ViewMatchers.withId(R.id.rvCopeContent), 0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withId(R.id.tvCreatedAt))))
    }

    @Test
    fun `should  display content created at`() {
        val activity = rule.activity

        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.rvCopeContent)
        (recyclerView.adapter as CopeDetailAdapter).add(
            listOf(
                ViewCopeContent("1", "This is a content that was search", dateFormatter.parse("Jun 12, 2019"),
                    dateFormatter.parse("Jun 13, 2019"))
            )
        )

        Espresso.onView(nthChildOf(ViewMatchers.withId(R.id.rvCopeContent), 0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Created at: Jun 12, 2019"))))
    }

    @Test
    fun `should have a view to display content updated at`() {
        val activity = rule.activity

        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.rvCopeContent)
        (recyclerView.adapter as CopeDetailAdapter).add(
            listOf(
                ViewCopeContent("1", "This is a content that was search", dateFormatter.parse("Jun 12, 2019"),
                    dateFormatter.parse("Jun 13, 2019"))
            )
        )

        Espresso.onView(nthChildOf(ViewMatchers.withId(R.id.rvCopeContent), 0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withId(R.id.tvUpdatedAt))))
    }

    @Test
    fun `should  display content update at`() {
        val activity = rule.activity

        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.rvCopeContent)
        (recyclerView.adapter as CopeDetailAdapter).add(
            listOf(
                ViewCopeContent("1", "This is a content that was search", dateFormatter.parse("Jun 12, 2019"),
                    dateFormatter.parse("Jun 13, 2019"))
            )
        )

        Espresso.onView(nthChildOf(ViewMatchers.withId(R.id.rvCopeContent), 0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Updated at: Jun 13, 2019"))))
    }
}

fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("with $childPosition child view of type parentMatcher")
        }

        override fun matchesSafely(view: View): Boolean {
            if (view.parent !is ViewGroup) {
                return parentMatcher.matches(view.parent)
            }

            val group = view.parent as ViewGroup
            return parentMatcher.matches(view.parent) && group.getChildAt(childPosition) == view
        }
    }
}
