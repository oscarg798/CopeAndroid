package com.nequi.copedetail.domian

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cope.core.models.ViewCopeContent
import com.nequi.copedetail.R

/**
 * @author Oscar Gallon on 2019-06-12.
 */
class CopeDetailAdapter(private val copeContents: ArrayList<ViewCopeContent> = ArrayList()) : RecyclerView.Adapter<CopeContentItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CopeContentItemViewHolder {
        return CopeContentItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cope_content_item, parent, false))
    }

    override fun getItemCount(): Int {
       return copeContents.size
    }

    override fun onBindViewHolder(holder: CopeContentItemViewHolder, position: Int) {
        holder.bind(copeContents[position])
    }

    fun add(copeContents: List<ViewCopeContent>) {
        this.copeContents.clear()
        this.copeContents.addAll(copeContents)
        notifyDataSetChanged()
    }
}
