package com.example.financeapp.ui.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.infrastructure.firebase.FirebaseAuthService
import com.example.financeapp.domain.infrastructure.firebase.FirestoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService,
    private val firestoreService: FirestoreService
) : ViewModel() {

    companion object {
        private const val TAG = "SignUpViewModel"
    }

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState.asStateFlow()

    fun signUp(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            try {
                _signUpState.value = SignUpState.Loading
                Log.d(TAG, "🚀 Iniciando proceso de registro")

                // Validar que los campos no estén vacíos
                if (fullName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                    Log.w(TAG, "⚠️ Campos vacíos")
                    _signUpState.value = SignUpState.Error(
                        "Por favor completa todos los campos"
                    )
                    return@launch
                }

                // Validar que las contraseñas coincidan
                if (password != confirmPassword) {
                    Log.w(TAG, "⚠️ Contraseñas no coinciden")
                    _signUpState.value = SignUpState.Error(
                        "Las contraseñas no coinciden"
                    )
                    return@launch
                }

                // Validar longitud mínima de contraseña
                if (password.length < 6) {
                    Log.w(TAG, "⚠️ Contraseña muy corta")
                    _signUpState.value = SignUpState.Error(
                        "La contraseña debe tener al menos 6 caracteres"
                    )
                    return@launch
                }

                // Validar formato de email
                if (!isValidEmail(email)) {
                    Log.w(TAG, "⚠️ Email inválido: $email")
                    _signUpState.value = SignUpState.Error(
                        "El email no es válido"
                    )
                    return@launch
                }

                Log.d(TAG, "✅ Validaciones completadas")

                // Crear usuario en Firebase Auth
                Log.d(TAG, "🔐 Creando usuario en Firebase Auth")
                val uid = firebaseAuthService.signUp(
                    email = email,
                    password = password,
                    displayName = fullName
                )
                Log.d(TAG, "✅ Usuario creado en Firebase Auth con UID: $uid")

                // Usar NonCancellable para garantizar que esta operación se complete
                withContext(NonCancellable) {
                    Log.d(TAG, "💾 INICIANDO guardado en Firestore dentro de NonCancellable")
                    try {
                        firestoreService.saveUser(
                            uid = uid,
                            email = email,
                            displayName = fullName
                        )
                        Log.d(TAG, "✅ Usuario guardado en Firestore (NonCancellable)")
                    } catch (e: Exception) {
                        Log.e(TAG, "❌ Error CRÍTICO al guardar en Firestore: ${e.message}", e)
                    }
                }

                // Emitir éxito DESPUÉS de que el bloque NonCancellable se complete
                _signUpState.value = SignUpState.Success(
                    uid = uid,
                    email = email,
                    fullName = fullName
                )
                Log.d(TAG, "🎉 Registro completado. Estado Success emitido.")

            } catch (e: Exception) {
                val errorMsg = e.message ?: "Error desconocido en el registro"
                Log.e(TAG, "❌ Error en el flujo de registro: $errorMsg", e)
                _signUpState.value = SignUpState.Error(errorMsg)
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
        _signUpState.value = SignUpState.Idle
    }
}

sealed class SignUpState {
    data object Idle : SignUpState()
    data object Loading : SignUpState()
    data class Success(
        val uid: String,
        val email: String,
        val fullName: String
    ) : SignUpState()
    data class Error(val message: String) : SignUpState()
}

