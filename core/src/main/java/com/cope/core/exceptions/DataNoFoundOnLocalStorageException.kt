package com.cope.core.exceptions

import com.cope.core.constants.LocalStorageKey

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class DataNoFoundOnLocalStorageException(key: LocalStorageKey) : NullPointerException("No data found for key $key")
