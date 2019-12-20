package com.cope.core.di

import com.google.firebase.perf.FirebasePerformance
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TrackingInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val (url: HttpUrl, method: String) = request
        val metric = FirebasePerformance.getInstance().newHttpMetric(
            url.toString(),
            method
        )
        metric.start()
        metric.setRequestPayloadSize(request.body()?.contentLength() ?: 0)
        val response = chain.proceed(request)
        metric.setHttpResponseCode(response.code())
        metric.stop()

        return response
    }


}

private operator fun Request.component2(): String = method()
private operator fun Request.component1(): HttpUrl = url()
