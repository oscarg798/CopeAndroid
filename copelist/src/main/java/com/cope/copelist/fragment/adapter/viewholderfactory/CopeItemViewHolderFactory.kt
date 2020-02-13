package com.cope.copelist.fragment.adapter.viewholderfactory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cope.copelist.R
import com.cope.copelist.fragment.adapter.CopeItemViewHolder
import com.cope.copelist.fragment.adapter.CopeViewHolder
import com.cope.core.featureflags.FeatureFlagHandler
import com.cope.core.models.FeatureFlag

class CopeItemViewHolderFactory(private val featureFlagHandler: FeatureFlagHandler) :
    ViewHolderFactory<CopeViewHolder> {

    override fun isApplicable(): Boolean {
        return !featureFlagHandler.isFeatureEnabled(FeatureFlag.NewList)
    }

    override fun build(parent: ViewGroup): CopeViewHolder {
        return CopeItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cope_item,
                parent,
                false
            )
        )
    }
}