package com.cope.copelist.fragment.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cope.copelist.fragment.CopeClickListener
import com.cope.copelist.fragment.adapter.viewholderfactory.ViewHolderFactory
import com.cope.core.models.Cope

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class CopeAdapter(
    private val copeClickListener: CopeClickListener,
    private val viewHolderFactories: List<ViewHolderFactory<CopeViewHolder>>,
    private val copes: ArrayList<Cope> = ArrayList()
) :
    RecyclerView.Adapter<CopeViewHolder>() {

    override fun getItemCount(): Int {
        return copes.size
    }

    override fun onBindViewHolder(holder: CopeViewHolder, position: Int) {
        holder.bind(copes[position], copeClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CopeViewHolder {
        return viewHolderFactories.first {
            it.isApplicable()
        }.build(parent)
    }

    fun add(copes: List<Cope>) {
        this.copes.clear()
        this.copes.addAll(copes)
        notifyDataSetChanged()
    }
}
