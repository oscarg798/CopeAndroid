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

package com.cope.core.di

import android.content.Context
import arrow.core.Either
import com.cope.core.constants.BASE_URL
import com.cope.core.constants.COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.CoroutineContextProvider
import com.cope.core.DynamicFeatureMappers
import com.cope.core.exceptions.FirebaseRemoteConfigInitializationException
import com.cope.core.featureflags.FirebaseRemoteConfigInitializator
import com.cope.core.interactor.InitFirebaseRemoteConfigUseCase
import com.cope.core.interactor.Interactor
import com.cope.logger.CopeLogger
import com.cope.logger.CountlyLoggerProcessor
import com.cope.logger.Logger
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import ly.count.android.sdk.Countly
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@Module
class CoreModule(private val appContext: Context) {

    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @CoreComponentScope
    @Named(COROUTINE_IO_CONTEXT_PROVIDER)
    @Provides
    fun provideIOCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider(Dispatchers.Main, Dispatchers.IO)
    }

    @CoreComponentScope
    @Named(COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER)
    @Provides
    fun provideComputationCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider(Dispatchers.Main, Dispatchers.Default)
    }

    @CoreComponentScope
    @Provides
    fun provideBaseUrl(): String {
        return BASE_URL
    }

    @Provides
    fun provideCountly(): Countly {
        return Countly.sharedInstance()
    }

    @CoreComponentScope
    @Provides
    fun provideLogger(context: Context, countly: Countly): Logger {
        return CopeLogger(listOf(CountlyLoggerProcessor(context, countly)))
    }

    @CoreComponentScope
    @Provides
    fun provideInitFirebaseRemoteConfigUseCase(initializator: FirebaseRemoteConfigInitializator): Interactor<Either<FirebaseRemoteConfigInitializationException, Unit>, Unit> {
        return InitFirebaseRemoteConfigUseCase(initializator)
    }

    @CoreComponentScope
    @Provides
    fun provideDynamicFeatureMappers(): DynamicFeatureMappers{
        return DynamicFeatureMappers()
    }
}
