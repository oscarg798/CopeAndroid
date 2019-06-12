package com.cope.core.di

import android.content.Context
import android.preference.PreferenceManager
import com.cope.core.repositories.LocalStorageRepository
import com.cope.core.repositories.LocalStorageRepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * @author Oscar Gallon on 2019-06-11.
 */
@Module
class RepositoryModule(private val context: Context) {

    @Provides
    fun provideLocalStorageRepository(): LocalStorageRepository {
        return LocalStorageRepositoryImpl(context) {
            PreferenceManager.getDefaultSharedPreferences(context)
        }
    }
}
