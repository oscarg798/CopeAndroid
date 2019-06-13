package com.nequi.copedetail

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.cope.core.constants.ARGUMENT_COPE
import com.cope.core.models.ViewCope
import com.cope.core.models.ViewCopeContent
import com.nequi.copedetail.domian.CopeDetailActivity
import com.nequi.copedetail.domian.CopeDetailAdapter
import org.amshove.kluent.shouldEqual
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-12.
 */
@RunWith(AndroidJUnit4::class)
class CopeDetailActivityTest {

    @get:Rule
    val rule = object : ActivityTestRule<CopeDetailActivity>(CopeDetailActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return Intent().apply {
                putExtra(ARGUMENT_COPE, ViewCope("1", "www.google.com", "Google", Date(), Date(), listOf()))
            }
        }
    }

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
                ViewCopeContent("1", "This is a content that was search")
            )
        )
        Espresso.onView(nthChildOf(ViewMatchers.withId(R.id.rvCopeContent), 0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("This is a content that was search"))))
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
