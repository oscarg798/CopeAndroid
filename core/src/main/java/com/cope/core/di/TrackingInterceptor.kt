package com.cope.core.di

import com.google.firebase.FirebaseApp
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.metrics.HttpMetric
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TrackingInterceptor : Interceptor {

    private var metric: HttpMetric? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        startTracking(request)
        val response = chain.proceed(request)
        stopMetricts(response.code())
        return response
    }

    private fun startTracking(request: Request) {
        val (url: HttpUrl, method: String) = request
        metric = FirebasePerformance.getInstance().newHttpMetric(
            url.toString(),
            method
        )
        metric?.setRequestPayloadSize(request.body()?.contentLength() ?: 0)
        metric?.start()
    }

    private fun stopMetricts(responseCode: Int) {
        metric?.setHttpResponseCode(responseCode)
        metric?.stop()
    }


}

private operator fun Request.component2(): String = method()
private operator fun Request.component1(): HttpUrl = url()
