package com.example.financeapp.infrastructure.api

import com.example.financeapp.infrastructure.api.request.LoginRequest
import com.example.financeapp.infrastructure.api.request.SignUpRequest
import com.example.financeapp.infrastructure.api.response.LoginResponse
import com.example.financeapp.infrastructure.api.response.SignUpResponse
import com.example.financeapp.infrastructure.api.response.TransactionsResponse
import com.example.financeapp.infrastructure.api.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/auth/create")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @GET("transactions")
    suspend fun getTransactions(): Response<TransactionsResponse>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") userId: Int
    ): Response<UserResponse>
}

