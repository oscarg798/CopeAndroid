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

package com.cope.copelist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.cope.copelist.di.CopeListModule
import com.cope.copelist.di.DaggerCopeListComponent
import com.cope.copelist.fragment.CopeListFragment
import com.cope.core.InjectableActivity
import com.cope.core.constants.COPE_LIST_DEEP_LINK
import com.cope.core.constants.LOGIN_DEEPLINK
import com.cope.core.di.CoreComponentProvider
import com.cope.core.di.injector.Injector
import com.cope.core.di.injector.InjectorProvider
import com.cope.core.extensions.startDeepLinkIntent
import javax.inject.Inject

@DeepLink(COPE_LIST_DEEP_LINK)
class CopeListActivity : InjectableActivity(), CopeListContract.View {

    @Inject
    lateinit var presenter: CopeListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cope_list)

        presenter.bind(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.clMain, CopeListFragment.newInstance(), CopeListFragment::class.java.name)
            .commitAllowingStateLoss()
    }

    override fun returnToLogIn() {
        startDeepLinkIntent(LOGIN_DEEPLINK)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            menuInflater.inflate(R.menu.cope_list_menu, menu)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        item?.let {
            if (item.itemId == R.id.logoutAction) {
                presenter.onLogoutPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
