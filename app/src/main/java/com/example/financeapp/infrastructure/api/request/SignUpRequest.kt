package com.example.financeapp.infrastructure.api.request

data class SignUpRequest(
    val id: Int,
    val username: String,
    val email: String,
    val password: String
)
