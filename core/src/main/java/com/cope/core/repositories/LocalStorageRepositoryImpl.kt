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

package com.cope.core.repositories

import android.content.Context
import com.cope.core.constants.LocalStorageKey
import com.cope.core.constants.sharePreferenceGetter
import com.cope.core.exceptions.DataNoFoundOnLocalStorageException
import com.google.gson.Gson

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class LocalStorageRepositoryImpl(
    private val context: Context,
    private val SharePreferenceGetter: sharePreferenceGetter
) : LocalStorageRepository {

    private val gson = Gson()

    override fun <T> getData(key: LocalStorageKey, classType: Class<T>): T {
        val preference = SharePreferenceGetter(context)
        val data = preference.getString(key, null) ?: throw DataNoFoundOnLocalStorageException(key)
        return gson.fromJson(data, classType)
    }

    override fun <T> saveData(key: LocalStorageKey, value: T) {
        val preferenceEditor = SharePreferenceGetter(context).edit()
        preferenceEditor.putString(key, gson.toJson(value))
        preferenceEditor.apply()
    }

    override fun clear() {
        SharePreferenceGetter(context).edit().clear().apply()
    }
}
