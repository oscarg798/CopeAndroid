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

import com.cope.copelist.fragment.adapter.copegroup.CopeGroup
import com.cope.core.CoroutineContextProvider
import com.cope.core.featureflags.FeatureFlagHandler
import com.cope.core.interactor.Interactor
import com.cope.core.mapper.ViewCopeMapper
import com.cope.core.models.Cope
import com.cope.core.models.None
import com.cope.core.models.ViewCope
import com.cope.logger.LogEvent
import com.cope.logger.Logger
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import java.net.URL


/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeListPresenter(
    private val getCopeInteractor: Interactor<List<Cope>, None>,
    private val viewCopeMapper: ViewCopeMapper,
    private val logger: Logger,
    private val featureFlagHandler: FeatureFlagHandler,
    override val coroutinesContextProvider: CoroutineContextProvider
) : CopeListContract.Presenter {

    override var view: CopeListContract.View? = null
    override val parentJob: Job = Job()

    override fun onViewCreated() {
        logger.log(LogEvent.View(CopeListFragment::class.java))
        getCopes()
    }

    override fun onRefresh() {
        if (parentJob.children.filter { it.isActive }.count() > 0) {
            view?.hideProgressDialog()
            return
        }

        onViewCreated()
    }

    override fun onCopeClick(cope: ViewCope) {
        launchJobOnMainDispatchers {
            view?.openCopeDetails(cope)
        }
    }

    private fun getCopes() {
        view?.showProgressDialog()
        launchJobOnMainDispatchers {
            runCatching {
                withContext(coroutinesContextProvider.backgroundContext) {
                    val groups = getCopeInteractor(None).map {
                        viewCopeMapper.map(it)
                    }.sortedByDescending { it.updateAt }

                    createCopeGroup(groups)
                }
            }.fold({
                view?.showCopes(it)
            }, {
                it.printStackTrace()
            })

            view?.hideProgressDialog()
        }
    }

    private fun createCopeGroup(copes: List<ViewCope>): List<CopeGroup> {
        val groups = HashMap<String, CopeGroup>()

        copes.forEach {viewCope->
            val url = getHostFromUrl(viewCope.url)
            if (groups.containsKey(url)) {
                val group = groups[url]
                val items = group!!.items
                groups[url] = CopeGroup(group.title, ArrayList<ViewCope>().apply {
                    addAll(items)
                    add(viewCope)
                }, viewCope.icon ?: group.icon)
            } else {
                groups[url] = CopeGroup(url, listOf(viewCope), viewCope.icon)
            }
        }

        return groups.mapTo(ArrayList<CopeGroup>(), {
            it.value
        })
    }

    private fun getHostFromUrl(url: String): String {
        return runCatching {
            val urlObject = URL(url)
            urlObject.getHost()
        }.fold({
            it
        }, {
            "ERROR"
        })
    }

    override fun handleException(error: Throwable) {
        super.handleException(error)
        view?.showError(error.message ?: "Something went wrong")

    }
}
