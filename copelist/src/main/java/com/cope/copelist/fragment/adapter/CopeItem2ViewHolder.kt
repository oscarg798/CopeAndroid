package com.cope.copelist.fragment.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
class CopeItem2ViewHolder(itemView: View) : CopeViewHolder(itemView) {

    private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    private val tvUrl = itemView.findViewById<TextView>(R.id.tvUrl)
    private val ivICon = itemView.findViewById<ImageView>(R.id.ivIcon)

    override fun bind(cope: Cope, copeClickListener: CopeClickListener) {
        tvTitle.text = cope.title
        tvUrl.text = cope.url

        itemView.setOnClickListener {
            copeClickListener.onCopeClick(cope)
        }

        val iconView = ivICon ?: return
        val iconUrl = cope.icon ?: return
        Picasso.get().load(iconUrl).into(iconView)
    }
}
