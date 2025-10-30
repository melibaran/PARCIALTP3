package com.example.financeapp.infrastructure.api

import com.example.financeapp.domain.infrastructure.api.ApiClient
import com.example.financeapp.domain.model.LoginRequest as DomainLoginRequest
import com.example.financeapp.domain.model.SignUpRequest as DomainSignUpRequest
import com.example.financeapp.domain.model.UserToken
import com.example.financeapp.domain.model.UserProfile
import com.example.financeapp.domain.model.UserTransactions
import com.example.financeapp.infrastructure.api.request.LoginRequest
import com.example.financeapp.infrastructure.api.request.SignUpRequest
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClientMockServer(
    private val baseUrl: String = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/"
) : ApiClient {

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

    override suspend fun login(request: DomainLoginRequest): UserToken {
        val infraRequest = LoginRequest.fromDomain(request)
        val response = apiService.login(infraRequest)
        return response.body()!!.toDomain()
    }

    override suspend fun signUp(request: DomainSignUpRequest): UserProfile {
        val infraRequest = SignUpRequest.fromDomain(request)
        val response = apiService.signUp(infraRequest)
        return response.body()!!.toDomain()
    }

    override suspend fun getTransactions(apiKey: String): UserTransactions {
        val response = apiService.getTransactions(apiKey)
        return response.body()!!.toDomain()
    }
}

