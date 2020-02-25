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
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import co.com.sharedialog.ShareDialogFragment
import com.airbnb.deeplinkdispatch.DeepLink
import com.cope.core.InjectableActivity
import com.cope.core.constants.ARGUMENT_COPE
import com.cope.core.constants.ARGUMENT_COPE_CONTENT
import com.cope.core.constants.COPE_CONTENT_DETAIL_DEEP_LINK
import com.cope.core.constants.COPE_DETAIL_DEEP_LINK
import com.cope.core.extensions.startDeepLinkIntent
import com.cope.core.models.ViewCope
import com.cope.core.models.ViewCopeContent
import kotlinx.android.synthetic.main.activity_cope_detail.*
import javax.inject.Inject

@DeepLink(COPE_DETAIL_DEEP_LINK)
class CopeDetailActivity : InjectableActivity(), CopeDetailContract.View {

    @Inject
    lateinit var presenter: CopeDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cope_detail)

        initComponents()

        tvCopeUrl.setOnClickListener {
            presenter.onCopeSourceClicked()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
        val viewCope =
            intent?.extras?.getParcelable<ViewCope>(ARGUMENT_COPE) ?: throw RuntimeException()
        presenter.onViewCreated(viewCope)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            menuInflater.inflate(R.menu.cope_detail_menu, menu)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        item?.let {
            if (item.itemId == R.id.actionShare) {
                presenter.onSharePressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showCopeContents(viewCopeContents: List<ViewCopeContent>) {
        (rvCopeContent?.adapter as? CopeDetailAdapter)?.add(viewCopeContents)
    }

    override fun showCopeContentDetail(viewCopeContent: ViewCopeContent) {
        startDeepLinkIntent(COPE_CONTENT_DETAIL_DEEP_LINK, Bundle().apply {
            putParcelable(ARGUMENT_COPE_CONTENT, viewCopeContent)
        })
    }

    override fun openCopeSource(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        })
    }

    override fun showCopeTitle(title: String) {
        tvCopeTitle?.text = title
    }

    override fun showCopeUrl(url: String) {
        tvCopeUrl?.text = url
    }

    override fun openShareDialog(copeId: String) {
        val dialog = ShareDialogFragment.newInstance(copeId)

        dialog.show(supportFragmentManager, ShareDialogFragment::class.java.name)
    }

    private fun initComponents() {
        rvCopeContent?.setHasFixedSize(false)
        rvCopeContent?.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvCopeContent?.adapter = CopeDetailAdapter(presenter)
    }
}
