package com.example.financeapp.domain.model

data class SignUpRequest(
    val id: Int,
    val username: String,
    val email: String,
    val password: String
) {
    init {
        require(id > 0) { "El ID debe ser mayor a 0" }
        require(username.isNotBlank()) { "El nombre de usuario no puede estar vacío" }
        require(username.length >= 3) { "El nombre de usuario debe tener al menos 3 caracteres" }
        require(email.isNotBlank()) { "El email no puede estar vacío" }
        require(password.isNotBlank()) { "La contraseña no puede estar vacía" }
        require(password.length >= 8) { "La contraseña debe tener al menos 8 caracteres" }
    }
}

