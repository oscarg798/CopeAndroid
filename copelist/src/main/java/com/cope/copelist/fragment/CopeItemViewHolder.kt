package com.cope.copelist.fragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cope.copelist.R
import com.cope.core.constants.DISPLAY_DATE_FORMAT
import com.cope.core.models.Cope
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    private val tvUrl = itemView.findViewById<TextView>(R.id.tvUrl)
    private val tvCreatedAt = itemView.findViewById<TextView>(R.id.tvCreatedAt)
    private val ivICon = itemView.findViewById<ImageView>(R.id.ivIcon)
    private val tvUpdatedAt = itemView.findViewById<TextView>(R.id.tvUpdatedAt)

    private val dateFormatter = SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.ENGLISH)

    fun bind(cope: Cope, copeClickListener: CopeClickListener) {
        tvTitle?.text = cope.title
        tvUrl?.text = cope.url
        tvCreatedAt?.text =
            String.format(itemView.context.getString(R.string.created_at_format), dateFormatter.format(cope.createdAt))
        tvUpdatedAt?.text =
            String.format(itemView.context.getString(R.string.updated_at_format), dateFormatter.format(cope.updateAt))

        itemView.setOnClickListener {
            copeClickListener.onCopeClick(cope)
        }

        val iconView = ivICon ?: return
        val iconUrl = cope.icon ?: return
        Picasso.get().load(iconUrl).into(iconView)

    }
}
