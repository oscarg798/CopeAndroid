package com.cope.core.featureflags

import com.cope.core.models.FeatureFlag

interface FeatureFlagHandler {

    fun isFeatureEnabled(featureFlag: FeatureFlag): Boolean
}