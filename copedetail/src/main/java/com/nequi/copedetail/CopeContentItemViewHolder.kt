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

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cope.core.constants.DISPLAY_DATE_FORMAT
import com.cope.core.models.ViewCopeContent
import com.nequi.copedetail.colorfactory.CopeContentColorFactory
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class CopeContentItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvText = itemView.findViewById<TextView>(R.id.tvText)
    private val clContent = itemView.findViewById<ConstraintLayout>(R.id.clContent)
    private val tvCreatedAt = itemView.findViewById<TextView>(R.id.tvCreatedAt)
    private val tvUpdatedAt = itemView.findViewById<TextView>(R.id.tvUpdatedAt)

    private val dateFormatter = SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.ENGLISH)

    fun bind(viewCopeContent: ViewCopeContent, copeContentClickListener: CopeContentClickListener) {
        tvText?.text = viewCopeContent.text
        clContent?.background = ColorDrawable(CopeContentColorFactory.getBackgroundColor())
        tvCreatedAt?.text =
            String.format(itemView.context.getString(R.string.created_at_format), dateFormatter.format(viewCopeContent.createdAt))
        tvUpdatedAt?.text =
            String.format(itemView.context.getString(R.string.updated_at_format), dateFormatter.format(viewCopeContent.updatedAt))

        itemView.setOnClickListener {
            copeContentClickListener.onCopeConntentClickListener(viewCopeContent)
        }
    }
}
