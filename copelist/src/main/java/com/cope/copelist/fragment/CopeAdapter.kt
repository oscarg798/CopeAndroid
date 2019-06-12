package com.cope.copelist.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cope.copelist.R
import com.cope.core.models.Cope

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeAdapter(private val copes: ArrayList<Cope> = ArrayList()) : RecyclerView.Adapter<CopeItemViewHolder>() {

    override fun getItemCount(): Int {
        return copes.size
    }

    override fun onBindViewHolder(holder: CopeItemViewHolder, position: Int) {
        holder.bind(copes[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CopeItemViewHolder {
        return CopeItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cope_item,
                parent,
                false
            )
        )
    }

    fun add(copes: List<Cope>){
        this.copes.addAll(copes)
        notifyDataSetChanged()
    }
}
