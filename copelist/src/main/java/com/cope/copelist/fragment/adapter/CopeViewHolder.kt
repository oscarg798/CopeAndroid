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

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cope.copelist.fragment.CopeClickListener
import com.cope.core.models.Cope

abstract class CopeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(cope: View, copeClickListener: CopeClickListener)
}