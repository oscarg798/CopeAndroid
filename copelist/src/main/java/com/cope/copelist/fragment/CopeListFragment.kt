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

package com.cope.copelist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cope.copelist.R
import com.cope.copelist.di.CopeListModule
import com.cope.copelist.di.DaggerCopeListComponent
import com.cope.copelist.fragment.adapter.CopeAdapter
import com.cope.copelist.fragment.adapter.CopeExpandableAdapter
import com.cope.copelist.fragment.adapter.CopeViewHolder
import com.cope.copelist.fragment.adapter.copegroup.CopeGroup
import com.cope.copelist.fragment.adapter.viewholderfactory.ViewHolderFactory
import com.cope.core.InjectableFragment
import com.cope.core.constants.ARGUMENT_COPE
import com.cope.core.constants.COPE_DETAIL_DEEP_LINK
import com.cope.core.constants.StringResourceId
import com.cope.core.di.CoreComponentProvider
import com.cope.core.di.injector.InjectorProvider
import com.cope.core.extensions.startDeepLinkIntent
import com.cope.core.models.Cope
import com.cope.core.models.ViewCope
import kotlinx.android.synthetic.main.fragment_cope_list.*
import javax.inject.Inject

class CopeListFragment : InjectableFragment(R.layout.fragment_cope_list), CopeListContract.View {

    @Inject
    lateinit var presenter: CopeListContract.Presenter

    @Inject
    lateinit var viewHolderFactories: List<@JvmSuppressWildcards ViewHolderFactory<@JvmSuppressWildcards CopeViewHolder>>

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = context ?: return
        rvCopes?.layoutManager = LinearLayoutManager(context)
        rvCopes?.setHasFixedSize(true)

        srMain?.setOnRefreshListener(presenter)

        presenter.onViewCreated()
    }

    override fun openCopeDetails(viewCope: ViewCope) {
        (activity as? AppCompatActivity)?.startDeepLinkIntent(
            COPE_DETAIL_DEEP_LINK,
            Bundle().apply {
                putParcelable(ARGUMENT_COPE, viewCope)
            })
    }

    override fun showCopes(copes: List<CopeGroup>) {
        rvCopes?.adapter = CopeExpandableAdapter(copes, presenter)
    }

    override fun showError(error: String) {
        val context = context ?: return
        Toast.makeText(
            context, error,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showError(error: StringResourceId) {
        showError(getString(error))
    }

    override fun showProgressDialog() {
        srMain?.isRefreshing = true
    }

    override fun hideProgressDialog() {
        srMain?.isRefreshing = false
    }

    companion object {
        fun newInstance(): CopeListFragment {
            return CopeListFragment()
        }
    }
}
