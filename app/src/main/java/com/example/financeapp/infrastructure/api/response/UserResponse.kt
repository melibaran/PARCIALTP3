package com.example.financeapp.infrastructure.api.response

import com.example.financeapp.domain.model.UserAddress
import com.example.financeapp.domain.model.UserProfile

data class UserResponse(
    val address: Address,
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val name: Name,
    val phone: String
) {
    fun toDomain(): UserProfile {
        return UserProfile(
            id = id,
            email = email,
            username = username,
            password = password,
            firstName = name.firstname,
            lastName = name.lastname,
            phone = phone,
            address = address.toDomain()
        )
    }
}

