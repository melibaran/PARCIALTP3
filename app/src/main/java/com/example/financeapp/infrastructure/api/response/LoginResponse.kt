package com.example.financeapp.infrastructure.api.response

import com.example.financeapp.domain.model.UserToken

data class LoginResponse(
    val token : String
) {
    fun toDomain(): UserToken {
        return UserToken(token = token)
    }
}
