/*
 * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 * This file is part of Cope.
 * Cope is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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