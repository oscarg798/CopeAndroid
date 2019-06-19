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
