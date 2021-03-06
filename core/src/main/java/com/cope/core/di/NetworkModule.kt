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

import com.cope.core.constants.AUTH_INTERCEPTOR
import com.cope.core.constants.BACKEND_DATE_FORMAT
import com.cope.core.constants.TIME_OUT_SECONDS
import com.cope.core.constants.TRACKING_INTERCEPTOR
import com.cope.core.repositories.LocalStorageRepository
import com.cope.core.services.CopeService
import com.google.firebase.FirebaseApp
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@Module
object NetworkModule {

    @CoreComponentScope
    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setDateFormat(BACKEND_DATE_FORMAT)
            .create()
        return GsonConverterFactory.create(gson)
    }

    @Named(AUTH_INTERCEPTOR)
    @CoreComponentScope
    @Provides
    fun provideAuthorizationInterceptor(localStorageRepository: LocalStorageRepository): Interceptor {
        return AuthInterceptor(localStorageRepository)
    }

    @CoreComponentScope
    @Provides
    fun provideLogginInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }

    @Named(TRACKING_INTERCEPTOR)
    @CoreComponentScope
    @Provides
    fun provideTrackingInterceptor(): Interceptor = TrackingInterceptor()

    @CoreComponentScope
    @Provides
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @Named(
            TRACKING_INTERCEPTOR
        ) trackingInterceptor: Interceptor,
        @Named(AUTH_INTERCEPTOR) authInterceptor: Interceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(trackingInterceptor)
            .addInterceptor(authInterceptor)

        return builder.build()
    }

    @CoreComponentScope
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .client(httpClient)
            .build()
    }

    @CoreComponentScope
    @Provides
    fun provideGetCopeListService(retrofit: Retrofit): CopeService {
        return retrofit.create(CopeService::class.java)
    }

}
