package com.cope.core.featureflags

import com.cope.core.models.FeatureFlag

class CopeFeatureFlagHandler(private val childHandlers: List<FeatureFlagHandler>) :
    FeatureFlagHandler {

    override fun isFeatureEnabled(featureFlag: FeatureFlag): Boolean {
        return childHandlers.sumBy {
            if (it.isFeatureEnabled(featureFlag)) {
                1
            } else {
                0
            }
        } > 0
    }
}