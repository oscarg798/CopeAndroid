package com.nequi.copedetail.domian

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cope.core.models.ViewCopeContent
import com.nequi.copedetail.R

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class CopeContentItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvText = itemView.findViewById<TextView>(R.id.tvText)
    private val clContent = itemView.findViewById<ConstraintLayout>(R.id.clContent)

    fun bind(viewCopeContent: ViewCopeContent) {
        tvText?.text = viewCopeContent.text
        clContent?.background = ColorDrawable(CopeContentColorFactory.getBackgroundColor())
    }
}
