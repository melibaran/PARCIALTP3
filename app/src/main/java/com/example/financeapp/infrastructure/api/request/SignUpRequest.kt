package com.example.financeapp.infrastructure.api.request

import com.example.financeapp.domain.model.SignUpRequest as DomainSignUpRequest

data class SignUpRequest(
    val id: Int,
    val username: String,
    val email: String,
    val password: String
) {
    companion object {
        fun fromDomain(domainRequest: DomainSignUpRequest): SignUpRequest {
            return SignUpRequest(
                id = domainRequest.id,
                username = domainRequest.username,
                email = domainRequest.email,
                password = domainRequest.password
            )
        }
    }
}
