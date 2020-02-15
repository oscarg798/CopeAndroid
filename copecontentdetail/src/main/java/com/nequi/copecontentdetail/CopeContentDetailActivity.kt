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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.cope.core.constants.ARGUMENT_COPE_CONTENT
import com.cope.core.constants.COPE_CONTENT_DETAIL_DEEP_LINK
import com.cope.core.di.CoreComponentProvider
import com.cope.core.models.ViewCopeContent
import com.nequi.copecontentdetail.di.CopeContentDetailModule
import com.nequi.copecontentdetail.di.DaggerCopeContentDetailComponent
import com.nequi.copecontentdetail.exceptions.CopeContentArgumentNotFoundException
import kotlinx.android.synthetic.main.activity_cope_content_detail.*
import javax.inject.Inject

@DeepLink(COPE_CONTENT_DETAIL_DEEP_LINK)
class CopeContentDetailActivity : AppCompatActivity(), CopeContentDetailContract.View {

    @Inject
    lateinit var presenter: CopeContentDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cope_content_detail)

        DaggerCopeContentDetailComponent.builder()
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .copeContentDetailModule(CopeContentDetailModule)
            .build()
            .inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
        val copeContent = intent?.extras?.getParcelable<ViewCopeContent>(ARGUMENT_COPE_CONTENT)
            ?: throw CopeContentArgumentNotFoundException
        presenter.onViewCreated(copeContent)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun showCopeContent(content: String) {
        tvText?.text = content
    }
}
