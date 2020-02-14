package com.cope.core

typealias DynamicFeatureMap = Pair<String, String>

fun DynamicFeatureMap.featureName() = first
fun DynamicFeatureMap.packageName() = second