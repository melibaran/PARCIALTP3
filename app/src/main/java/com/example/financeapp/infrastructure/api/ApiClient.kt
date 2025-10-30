package com.example.financeapp.infrastructure.api

import com.example.financeapp.infrastructure.api.request.LoginRequest
import com.example.financeapp.infrastructure.api.request.SignUpRequest
import com.example.financeapp.infrastructure.api.response.LoginResponse
import com.example.financeapp.infrastructure.api.response.SignUpResponse
import com.example.financeapp.infrastructure.api.response.TransactionsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient(private val baseUrl: String = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/") {

    private val apiService: ApiService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun login(username: String, password: String): Response<LoginResponse> {
        val request = LoginRequest(username, password)
        return apiService.login(request)
    }

    suspend fun signUp(
        id: Int,
        username: String,
        email: String,
        password: String
    ): Response<SignUpResponse> {
        val request = SignUpRequest(id, username, email, password)
        return apiService.signUp(request)
    }

    suspend fun getTransactions(apiKey: String = "123456789"): Response<TransactionsResponse> {
        return apiService.getTransactions(apiKey)
    }
}