package com.example.financeapp.infrastructure.api

import com.example.financeapp.core.ApiKeyInterceptor
import com.example.financeapp.core.Config
import com.example.financeapp.domain.infrastructure.api.ApiClient
import com.example.financeapp.domain.model.UserProfile
import com.example.financeapp.domain.model.UserToken
import com.example.financeapp.domain.model.UserTransactions
import com.example.financeapp.infrastructure.api.request.LoginRequest
import com.example.financeapp.infrastructure.api.request.SignUpRequest
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.financeapp.domain.model.LoginRequest as DomainLoginRequest
import com.example.financeapp.domain.model.SignUpRequest as DomainSignUpRequest

class ApiClientMockServer(
    private val baseUrl: String = Config.baseUrl
) : ApiClient {

    private val apiService: ApiService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(ApiKeyInterceptor())
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

    override suspend fun getTransactions(): UserTransactions {
        val response = apiService.getTransactions()
        return response.body()!!.toDomain()
    }

    override suspend fun getUserById(userId: Int): UserProfile {
        val response = apiService.getUserById(userId)
        return response.body()!!.toDomain()
    }
}

