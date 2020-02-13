package com.cope.core.repositories

import com.cope.core.constants.LocalStorageKey

/**
 * @author Oscar Gallon on 2019-06-11.
 */
interface LocalStorageRepository {

    fun <T> saveData(key: LocalStorageKey, value: T)

    fun <T> getData(key: LocalStorageKey, classType: Class<T>): T

    fun clear()
}
