package com.cope.copelist.fragment.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cope.copelist.fragment.CopeClickListener
import com.cope.core.models.Cope

abstract class CopeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(cope: Cope, copeClickListener: CopeClickListener)
}