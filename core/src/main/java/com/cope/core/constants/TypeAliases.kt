package com.cope.core.constants

import android.content.Context
import android.content.SharedPreferences

/**
 * @author Oscar Gallon on 2019-06-06.
 */
typealias StringResourceId = Int

typealias Token = String
typealias DeepLink = String
typealias Password = String
typealias LocalStorageKey = String

typealias sharePreferenceGetter = (Context) -> SharedPreferences

typealias BackgroundColor =  Int
