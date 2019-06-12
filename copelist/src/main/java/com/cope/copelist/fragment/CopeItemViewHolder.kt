package com.cope.copelist.fragment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cope.copelist.R
import com.cope.core.models.Cope

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    private val tvUrl = itemView.findViewById<TextView>(R.id.tvUrl)

    fun bind(cope: Cope) {
        tvTitle?.text = cope.title
        tvUrl?.text = cope.url
    }
}
