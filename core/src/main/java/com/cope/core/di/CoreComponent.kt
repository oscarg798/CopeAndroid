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
import com.cope.core.CoroutineContextProvider
import com.cope.core.DynamicFeatureMappers
import com.cope.core.constants.COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER
import com.cope.core.constants.COROUTINE_IO_CONTEXT_PROVIDER
import com.cope.core.constants.FEATURE_FLAG_HANDLER
import com.cope.core.constants.FIREBASE_REMOTE_CONFIG_FEATURE_FLAG_HANDLER
import com.cope.core.featureflags.FeatureFlagHandler
import com.cope.core.featureflags.FirebaseRemoteConfigInitializator
import com.cope.core.interactor.Interactor
import com.cope.core.repositories.LocalStorageRepository
import com.cope.logger.Logger
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@CoreComponentScope
@Component(modules = [CoreModule::class, NetworkModule::class, RepositoryModule::class, FeatureFlagModule::class])
interface CoreComponent {

    @Named(COROUTINE_IO_CONTEXT_PROVIDER)
    fun provideIOCoroutineContextProvider(): CoroutineContextProvider

    @Named(COROUTINE_COMPUTATIONAL_CONTEXT_PROVIDER)
    fun provideComputationCoroutineContextProvider(): CoroutineContextProvider

    fun provideRetrofit(): Retrofit

    fun provideLocalStorageRepository(): LocalStorageRepository

    fun provideLogger(): Logger

    @Named(FIREBASE_REMOTE_CONFIG_FEATURE_FLAG_HANDLER)
    fun provideFirebaseRemoteConfigFeatureHandler(): FeatureFlagHandler

    @Named(FEATURE_FLAG_HANDLER)
    fun provideCopeFeatureFlagHandler(): FeatureFlagHandler

    fun provideFirebaseRemoteConfigInitializator(): FirebaseRemoteConfigInitializator

    fun provideInitFirebaseRemoteConfigUseCase(): Interactor<Unit, Unit>

    fun provideDynamicFeatureMappers(): DynamicFeatureMappers

    fun provideContext(): Context
}
