package com.cope.core.di

import com.cope.core.constants.AUTHORIZATION_HEADER
import com.cope.core.constants.TOKEN
import com.cope.core.repositories.LocalStorageRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val localStorageRepository: LocalStorageRepository) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        getToken()?.let {
            requestBuilder.addHeader(AUTHORIZATION_HEADER, "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }

    private fun getToken(): String? {
        return runCatching {
            localStorageRepository.getData(TOKEN, String::class.java)
        }.getOrNull()
    }
}