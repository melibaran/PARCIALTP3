package com.example.financeapp.domain.model


data class UserProfile(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val address: UserAddress
)

data class UserAddress(
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String,
    val latitude: String,
    val longitude: String
)

