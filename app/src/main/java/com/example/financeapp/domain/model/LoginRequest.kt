package com.example.financeapp.domain.model

data class LoginRequest(
    val username: String,
    val password: String
) {
    init {
        require(username.isNotBlank()) { "El nombre de usuario no puede estar vacío" }
        require(password.isNotBlank()) { "La contraseña no puede estar vacía" }
        require(password.length >= 6) { "La contraseña debe tener al menos 6 caracteres" }
    }
}

