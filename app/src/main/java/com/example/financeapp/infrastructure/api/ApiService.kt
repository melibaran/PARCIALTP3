package com.example.financeapp.infrastructure.api

import com.example.financeapp.infrastructure.api.request.LoginRequest
import com.example.financeapp.infrastructure.api.request.SignUpRequest
import com.example.financeapp.infrastructure.api.response.LoginResponse
import com.example.financeapp.infrastructure.api.response.SignUpResponse
import com.example.financeapp.infrastructure.api.response.TransactionsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/auth/create")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @GET("transactions")
    suspend fun getTransactions(
        @Header("api-key") apiKey: String
    ): Response<TransactionsResponse>
}

