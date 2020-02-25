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

import com.cope.core.CoroutineContextProvider
import com.cope.core.models.ViewCope
import com.cope.core.models.ViewCopeContent
import kotlinx.coroutines.Job

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class CopePresenter(override val coroutinesContextProvider: CoroutineContextProvider) :
    CopeDetailContract.Presenter {

    override var parentJob: Job = Job()
    override var view: CopeDetailContract.View? = null
    private lateinit var viewCope: ViewCope

    override fun onViewCreated(viewCope: ViewCope) {
        this.viewCope = viewCope
        view?.showCopeTitle(viewCope.title)
        view?.showCopeUrl(viewCope.url)
        view?.showCopeContents(viewCope.content)
    }

    override fun onCopeConntentClickListener(copeContent: ViewCopeContent) {
        view?.showCopeContentDetail(copeContent)
    }

    override fun onCopeSourceClicked() {
        view?.openCopeSource(viewCope.url)
    }

    override fun onSharePressed() {
        view?.openShareDialog(viewCope.id)
    }
}
