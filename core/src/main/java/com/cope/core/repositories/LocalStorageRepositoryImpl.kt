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
