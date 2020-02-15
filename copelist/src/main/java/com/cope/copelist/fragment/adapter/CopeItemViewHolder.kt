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
import android.widget.ImageView
import android.widget.TextView
import com.cope.copelist.R
import com.cope.copelist.fragment.CopeClickListener
import com.cope.core.constants.DISPLAY_DATE_FORMAT
import com.cope.core.models.Cope
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeItemViewHolder(itemView: View) : CopeViewHolder(itemView) {

    private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    private val tvUrl = itemView.findViewById<TextView>(R.id.tvUrl)
    private val tvCreatedAt = itemView.findViewById<TextView>(R.id.tvCreatedAt)
    private val ivICon = itemView.findViewById<ImageView>(R.id.ivIcon)
    private val tvUpdatedAt = itemView.findViewById<TextView>(R.id.tvUpdatedAt)

    private val dateFormatter = SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.ENGLISH)

    override fun bind(cope: Cope, copeClickListener: CopeClickListener) {
        tvTitle?.text = cope.title
        tvUrl?.text = cope.url
        tvCreatedAt?.text =
            String.format(
                itemView.context.getString(R.string.created_at_format),
                dateFormatter.format(cope.createdAt)
            )
        tvUpdatedAt?.text =
            String.format(
                itemView.context.getString(R.string.updated_at_format),
                dateFormatter.format(cope.updateAt)
            )

        itemView.setOnClickListener {
            copeClickListener.onCopeClick(cope)
        }

        val iconView = ivICon ?: return
        val iconUrl = cope.icon ?: return
        Picasso.get().load(iconUrl).into(iconView)
    }
}
