package com.example.financeapp.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.infrastructure.firebase.FirebaseAuthService
import com.example.financeapp.domain.infrastructure.firebase.FirestoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService,
    private val firestoreService: FirestoreService
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    init {
        // Verificar si el usuario ya está autenticado al inicializar
        checkAuthenticationStatus()
    }

    private fun checkAuthenticationStatus() {
        firebaseAuthService.getCurrentUser()?.let { user ->
            _loginState.value = LoginState.Success(
                uid = user.uid,
                email = user.email ?: "",
                fullName = user.displayName ?: ""
            )
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loginState.value = LoginState.Loading

                // Validar que los campos no estén vacíos
                if (email.isBlank() || password.isBlank()) {
                    _loginState.value = LoginState.Error(
                        "Por favor completa todos los campos"
                    )
                    return@launch
                }

                // Validar formato de email
                if (!isValidEmail(email)) {
                    _loginState.value = LoginState.Error(
                        "El email no es válido"
                    )
                    return@launch
                }

                // Intentar autenticación con Firebase
                val uid = firebaseAuthService.signIn(email, password)

                // Obtener datos del usuario desde Firestore
                val user = firestoreService.getUser(uid)

                _loginState.value = LoginState.Success(
                    uid = uid,
                    email = user?.email ?: email,
                    fullName = user?.displayName ?: ""
                )

            } catch (e: Exception) {
                _loginState.value = LoginState.Error(
                    e.message ?: "Error desconocido en el inicio de sesión"
                )
            }
        }
    }

    /**
     * Valida el formato básico del email
     */
    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    fun resetState() {
        _loginState.value = LoginState.Idle
    }
}

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(
        val uid: String,
        val email: String,
        val fullName: String
    ) : LoginState()
    data class Error(val message: String) : LoginState()
}

