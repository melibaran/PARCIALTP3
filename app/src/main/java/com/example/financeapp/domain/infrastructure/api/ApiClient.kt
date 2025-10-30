package com.example.financeapp.domain.infrastructure.api

import com.example.financeapp.domain.model.LoginRequest
import com.example.financeapp.domain.model.SignUpRequest
import com.example.financeapp.domain.model.UserToken
import com.example.financeapp.domain.model.UserProfile
import com.example.financeapp.domain.model.UserTransactions


interface ApiClient {

    suspend fun login(request: LoginRequest): UserToken

    suspend fun signUp(request: SignUpRequest): UserProfile

    suspend fun getTransactions(apiKey: String = "123456789"): UserTransactions
}

