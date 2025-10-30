package com.example.financeapp.infrastructure.api.response

import com.example.financeapp.domain.model.UserAddress
import com.example.financeapp.domain.model.UserProfile

data class SignUpResponse(
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

data class Address(
    val geolocation: Geolocation,
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String
) {
    fun toDomain(): UserAddress {
        return UserAddress(
            city = city,
            street = street,
            number = number,
            zipcode = zipcode,
            latitude = geolocation.lat,
            longitude = geolocation.long
        )
    }
}

data class Geolocation(
    val lat: String,
    val long: String
)

data class Name(
    val firstname: String,
    val lastname: String
)