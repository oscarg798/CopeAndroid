package com.nequi.copecontentdetail

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cope.core.CoreApplication
import com.cope.core.constants.ARGUMENT_COPE_CONTENT
import com.cope.core.models.ViewCopeContent
import com.nequi.copecontentdetail.exceptions.CopeContentArgumentNotFoundException
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
@Config(application = CoreApplication::class)
class CopeDetailActivityTest {

    @Test(expected = CopeContentArgumentNotFoundException::class)
    fun `should throw an exception if no cope content is provided`() {
        ActivityScenario.launch<CopeContentDetailActivity>(
            Intent(
                ApplicationProvider.getApplicationContext<CoreApplication>(),
                CopeContentDetailActivity::class.java
            )
        )
    }

    @Test
    fun `should show cope content text`() {
        val scenario = given {
            ActivityScenario.launch<CopeContentDetailActivity>(
                Intent(
                    ApplicationProvider.getApplicationContext<CoreApplication>(),
                    CopeContentDetailActivity::class.java
                ).apply {
                    putExtras(Bundle().apply {
                        putParcelable(ARGUMENT_COPE_CONTENT, ViewCopeContent("1", "2"))
                    })
                }
            )
        }

       whenever {
           scenario.onActivity {activity->
               then {
                   activity.findViewById<TextView>(R.id.tvText).text shouldEqual "2"
               }
           }
       }
    }
}
