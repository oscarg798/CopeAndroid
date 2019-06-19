package com.cope.copelist

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cope.copelist.fragment.CopeAdapter
import com.cope.copelist.fragment.CopeListFragment
import com.cope.core.CoreTestApplication
import com.cope.core.models.Cope
import org.amshove.kluent.shouldEqual
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-11.
 */
@RunWith(AndroidJUnit4::class)
@Config(manifest = "AndroidManifest.xml", application = CoreTestApplication::class)
class CopeListFragmentTest {

    @Test
    fun `should have a list of copes`() {
        val scenario = launchFragmentInContainer<CopeListFragment>(
            Bundle.EMPTY,
            android.R.style.ThemeOverlay_Material_Dark_ActionBar
        )
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment {
            it.view!!.findViewById<RecyclerView>(R.id.rvCopes).visibility shouldEqual View.VISIBLE
        }
    }

    @Test
    fun `should have a CopeAdapter`() {
        val scenario = launchFragmentInContainer<CopeListFragment>(
            Bundle.EMPTY,
            android.R.style.ThemeOverlay_Material_Dark_ActionBar
        )

        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment {
            val recyclerView = it.view!!.findViewById<RecyclerView>(R.id.rvCopes)
            (recyclerView.adapter is CopeAdapter) shouldEqual true
        }
    }

    @Test
    fun `should show a cope with google as title`() {
        val scenario = launchFragmentInContainer<CopeListFragment>(
            Bundle.EMPTY,
            android.R.style.ThemeOverlay_Material_Dark_ActionBar
        )

        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment {
            val recyclerView = it.view!!.findViewById<RecyclerView>(R.id.rvCopes)
            (recyclerView.adapter as CopeAdapter).add(
                listOf(
                    Cope(
                        "1", "www.google.com", "My search on google", Date(),
                        Date(), listOf()
                    )
                )
            )
            onView(nthChildOf(withId(R.id.rvCopes), 0))
                .check(matches(hasDescendant(withText("My search on google"))))
        }
    }

    @Test
    fun `should show a cope with My search on google as title and www google com as url`() {
        val scenario = launchFragmentInContainer<CopeListFragment>(
            Bundle.EMPTY,
            android.R.style.ThemeOverlay_Material_Dark_ActionBar
        )

        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment {
            val recyclerView = it.view!!.findViewById<RecyclerView>(R.id.rvCopes)
            (recyclerView.adapter as CopeAdapter).add(
                listOf(
                    Cope(
                        "1", "www.google.com", "My search on google", Date(),
                        Date(), listOf()
                    )
                )
            )
            onView(nthChildOf(withId(R.id.rvCopes), 0))
                .check(matches(hasDescendant(withText("My search on google"))))
            onView(nthChildOf(withId(R.id.rvCopes), 0))
                .check(matches(hasDescendant(withText("www.google.com"))))
        }
    }

    @Test
    fun `each cope should have an arrow indicator`() {
        val scenario = launchFragmentInContainer<CopeListFragment>(
            Bundle.EMPTY,
            android.R.style.ThemeOverlay_Material_Dark_ActionBar
        )

        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment {
            val recyclerView = it.view!!.findViewById<RecyclerView>(R.id.rvCopes)
            (recyclerView.adapter as CopeAdapter).add(
                listOf(
                    Cope(
                        "1", "www.google.com", "My search on google", Date(),
                        Date(), listOf()
                    )
                )
            )
            onView(nthChildOf(withId(R.id.rvCopes), 0))
                .check(
                    matches(
                        hasDescendant(
                            withId(R.id.ivArrowIndicator)
                        )
                    )
                )
        }
    }

    @Test
    fun `each cope should have its icon`() {
        val scenario = launchFragmentInContainer<CopeListFragment>(
            Bundle.EMPTY,
            android.R.style.ThemeOverlay_Material_Dark_ActionBar
        )

        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment {
            val recyclerView = it.view!!.findViewById<RecyclerView>(R.id.rvCopes)
            (recyclerView.adapter as CopeAdapter).add(
                listOf(
                    Cope(
                        "1", "www.google.com", "My search on google", Date(),
                        Date(), listOf()
                    )
                )
            )
            onView(nthChildOf(withId(R.id.rvCopes), 0))
                .check(
                    matches(
                        hasDescendant(
                            withId(R.id.ivIcon)
                        )
                    )
                )
        }
    }

    @Test
    fun `each cope should show created at`() {
        val scenario = launchFragmentInContainer<CopeListFragment>(
            Bundle.EMPTY,
            android.R.style.ThemeOverlay_Material_Dark_ActionBar
        )

        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment {
            val recyclerView = it.view!!.findViewById<RecyclerView>(R.id.rvCopes)
            (recyclerView.adapter as CopeAdapter).add(
                listOf(
                    Cope(
                        "1", "www.google.com", "My search on google", Date(),
                        Date(), listOf()
                    )
                )
            )
            onView(nthChildOf(withId(R.id.rvCopes), 0))
                .check(
                    matches(
                        hasDescendant(
                            withId(R.id.tvCreatedAt)
                        )
                    )
                )
        }
    }

    @Test
    fun `each cope should show updated at`() {
        val scenario = launchFragmentInContainer<CopeListFragment>(
            Bundle.EMPTY,
            android.R.style.ThemeOverlay_Material_Dark_ActionBar
        )

        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment {
            val recyclerView = it.view!!.findViewById<RecyclerView>(R.id.rvCopes)
            (recyclerView.adapter as CopeAdapter).add(
                listOf(
                    Cope(
                        "1", "www.google.com", "My search on google", Date(),
                        Date(), listOf()
                    )
                )
            )
            onView(nthChildOf(withId(R.id.rvCopes), 0))
                .check(
                    matches(
                        hasDescendant(
                            withId(R.id.tvUpdatedAt)
                        )
                    )
                )
        }
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
