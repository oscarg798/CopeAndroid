/*
 *
 *  * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 *  * This file is part of Cope.
 *  * Cope is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.cope.copelist.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cope.copelist.R
import com.cope.copelist.fragment.CopeClickListener
import com.cope.copelist.fragment.adapter.copegroup.CopeGroup
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class CopeExpandableAdapter(
    copeGroups: List<CopeGroup>,
    private val copeClickListener: CopeClickListener
) :
    ExpandableRecyclerViewAdapter<CopeGroupViewHolder, CopeItemViewHolder>(copeGroups) {

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): CopeGroupViewHolder {
        return CopeGroupViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cope_group_item,
                parent,
                false
            )
        )
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): CopeItemViewHolder {
        return CopeItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cope_item,
                parent,
                false
            )
        )
    }

    override fun onBindChildViewHolder(
        holder: CopeItemViewHolder,
        flatPosition: Int,
        group: ExpandableGroup<*>,
        childIndex: Int
    ) {
        holder.bind((group as CopeGroup).items[childIndex], copeClickListener)
    }

    override fun onBindGroupViewHolder(
        holder: CopeGroupViewHolder,
        flatPosition: Int,
        group: ExpandableGroup<*>
    ) {
        holder.bind(group as ExpandableGroup<CopeGroup>)
    }
}