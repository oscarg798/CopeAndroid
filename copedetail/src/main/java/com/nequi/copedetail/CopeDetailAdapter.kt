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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cope.core.models.ViewCopeContent

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class CopeDetailAdapter(
    private val copeContentClickListener: CopeContentClickListener,
    private val copeContents: ArrayList<ViewCopeContent> = ArrayList()
) : RecyclerView.Adapter<CopeContentItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CopeContentItemViewHolder {
        return CopeContentItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cope_content_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return copeContents.size
    }

    override fun onBindViewHolder(holder: CopeContentItemViewHolder, position: Int) {
        holder.bind(copeContents[position], copeContentClickListener)
    }

    fun add(copeContents: List<ViewCopeContent>) {
        this.copeContents.clear()
        this.copeContents.addAll(copeContents)
        notifyDataSetChanged()
    }
}
