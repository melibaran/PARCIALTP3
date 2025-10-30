package com.example.financeapp.infrastructure.api.request

import com.example.financeapp.domain.model.LoginRequest as DomainLoginRequest

data class LoginRequest(
    val username: String,
    val password: String
) {
    companion object {
        fun fromDomain(domainRequest: DomainLoginRequest): LoginRequest {
            return LoginRequest(
                username = domainRequest.username,
                password = domainRequest.password
            )
        }
    }
}
