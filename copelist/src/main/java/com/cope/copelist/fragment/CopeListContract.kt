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

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cope.core.constants.StringResourceId
import com.cope.core.contract.BasePresenter
import com.cope.core.contract.BaseView
import com.cope.core.models.ViewCope

/**
 * @author Oscar Gallon on 2019-06-11.
 */
interface CopeListContract {

    interface View : BaseView {

        fun showCopes(copes: List<ViewCope>)

        fun showProgressDialog()

        fun hideProgressDialog()

        fun showError(error: StringResourceId)

        fun showError(error: String)

        fun openCopeDetails(viewCope: ViewCope)
    }

    interface Presenter : BasePresenter<View>, SwipeRefreshLayout.OnRefreshListener, CopeClickListener {

        fun onViewCreated()
    }
}
