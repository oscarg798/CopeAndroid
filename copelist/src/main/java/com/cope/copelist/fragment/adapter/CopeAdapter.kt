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

package com.cope.copelist.fragment.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cope.copelist.fragment.CopeClickListener
import com.cope.copelist.fragment.adapter.viewholderfactory.ViewHolderFactory
import com.cope.core.models.Cope
import com.cope.core.models.ViewCope

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeAdapter(
    private val copeClickListener: CopeClickListener,
    private val viewHolderFactories: List<ViewHolderFactory<CopeViewHolder>>,
    private val copes: ArrayList<ViewCope> = ArrayList()
) :
    RecyclerView.Adapter<CopeViewHolder>() {

    override fun getItemCount(): Int {
        return copes.size
    }

    override fun onBindViewHolder(holder: CopeViewHolder, position: Int) {
        holder.bind(copes[position], copeClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CopeViewHolder {
        return viewHolderFactories.first {
            it.isApplicable()
        }.build(parent)
    }

    fun add(copes: List<ViewCope>) {
        this.copes.clear()
        this.copes.addAll(copes)
        notifyDataSetChanged()
    }
}
