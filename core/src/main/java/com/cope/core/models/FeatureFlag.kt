package com.cope.core.models

sealed class FeatureFlag(val key: String) {
    object NewList : FeatureFlag("new_List")
}