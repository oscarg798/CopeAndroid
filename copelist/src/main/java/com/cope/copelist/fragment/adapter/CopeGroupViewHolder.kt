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

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cope.copelist.R
import com.cope.copelist.fragment.adapter.copegroup.CopeGroup
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import java.lang.Exception

class CopeGroupViewHolder(itemView: View) : GroupViewHolder(itemView) {

    private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    private val tvCount = itemView.findViewById<TextView>(R.id.tvCount)

    fun bind(expandableGroup: ExpandableGroup<CopeGroup>) {
        val copeGroup = expandableGroup as CopeGroup
        tvTitle.setText(copeGroup.title)
        tvCount.text = copeGroup.itemCount.toString()
    }
}