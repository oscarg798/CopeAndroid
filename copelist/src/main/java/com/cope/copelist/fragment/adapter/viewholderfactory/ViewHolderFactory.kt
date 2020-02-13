package com.cope.copelist.fragment.adapter.viewholderfactory

import android.view.ViewGroup
import com.cope.copelist.fragment.adapter.CopeViewHolder

interface ViewHolderFactory<T : CopeViewHolder> {

    fun isApplicable(): Boolean

    fun build(parent: ViewGroup): T
}