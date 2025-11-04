package com.example.financeapp.core

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = Config.apiKey

        val request = chain.request().newBuilder()
            .header("x-api-key", apiKey)
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .build()
        
        return chain.proceed(request)
    }
}
