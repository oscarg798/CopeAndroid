package com.cope.copelist.fragment.adapter.viewholderfactory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cope.copelist.R
import com.cope.copelist.fragment.adapter.CopeItem2ViewHolder
import com.cope.copelist.fragment.adapter.CopeItemViewHolder
import com.cope.copelist.fragment.adapter.CopeViewHolder
import com.cope.core.featureflags.FeatureFlagHandler
import com.cope.core.models.FeatureFlag
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import java.util.*

class CopeItem2ViewHolderFactory(private val featureFlagHandler: FeatureFlagHandler) :
    ViewHolderFactory<CopeViewHolder> {

    override fun isApplicable(): Boolean {
        return featureFlagHandler.isFeatureEnabled(FeatureFlag.NewList)
    }

    override fun build(parent: ViewGroup): CopeViewHolder {
        return CopeItem2ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cope_item_2,
                parent,
                false
            )
        )
    }
}