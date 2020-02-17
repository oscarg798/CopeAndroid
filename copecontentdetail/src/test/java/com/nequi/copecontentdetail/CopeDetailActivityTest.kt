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
import java.util.*

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
        ActivityScenario.launch<CopeContentDetailActivity>(CopeContentDetailActivity::class.java)
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
                        putParcelable(ARGUMENT_COPE_CONTENT, ViewCopeContent("1", "2", Date(), Date()))
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
