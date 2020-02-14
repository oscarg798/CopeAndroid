package com.cope.copelist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.cope.copelist.di.CopeListModule
import com.cope.copelist.di.DaggerCopeListComponent
import com.cope.copelist.fragment.CopeListFragment
import com.cope.core.constants.COPE_LIST_DEEP_LINK
import com.cope.core.constants.LOGIN_DEEPLINK
import com.cope.core.di.CoreComponentProvider
import com.cope.core.extensions.startDeepLinkIntent
import javax.inject.Inject

@DeepLink(COPE_LIST_DEEP_LINK)
class CopeListActivity : AppCompatActivity(), CopeListContract.View {

    @Inject
    lateinit var presenter: CopeListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cope_list)

        DaggerCopeListComponent.builder()
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .copeListModule(CopeListModule)
            .build()
            .inject(this)

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
