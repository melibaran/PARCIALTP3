package com.example.financeapp.domain.infrastructure.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Servicio para manejar la autenticación con Firebase Auth
 */
@Singleton
class FirebaseAuthService @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    companion object {
        private const val TAG = "FirebaseAuthService"
    }

    /**
     * Registra un nuevo usuario con email y contraseña
     */
    suspend fun signUp(
        email: String,
        password: String,
        displayName: String
    ): String {
        return try {
            Log.d(TAG, "📝 Iniciando signUp para email: $email")

            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user ?: throw Exception("Usuario no creado")

            Log.d(TAG, "✅ Usuario creado en Firebase Auth con UID: ${user.uid}")

            // Actualizar el perfil del usuario con el nombre completo
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .build()

            user.updateProfile(profileUpdates).await()

            Log.d(TAG, "✅ Perfil actualizado con nombre: $displayName")

            user.uid
        } catch (e: FirebaseAuthException) {
            val errorMsg = when (e.errorCode) {
                "ERROR_INVALID_EMAIL" -> "El email no es válido"
                "ERROR_WEAK_PASSWORD" -> "La contraseña es muy débil (mínimo 6 caracteres)"
                "ERROR_EMAIL_ALREADY_IN_USE" -> "Este email ya está registrado"
                "ERROR_USER_DISABLED" -> "Este usuario ha sido deshabilitado"
                else -> e.message ?: "Error en el registro"
            }
            Log.e(TAG, "❌ Error FirebaseAuthException en signUp: $errorMsg", e)
            throw Exception(errorMsg)
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error general en signUp: ${e.message}", e)
            throw Exception(e.message ?: "Error desconocido en el registro")
        }
    }

    /**
     * Autentica un usuario existente
     */
    suspend fun signIn(email: String, password: String): String {
        return try {
            Log.d(TAG, "🔑 Iniciando signIn para email: $email")

            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid ?: throw Exception("No se pudo obtener el UID del usuario")

            Log.d(TAG, "✅ Usuario autenticado con UID: $uid")
            uid
        } catch (e: FirebaseAuthException) {
            val errorMsg = when (e.errorCode) {
                "ERROR_INVALID_EMAIL" -> "El email no es válido"
                "ERROR_USER_NOT_FOUND" -> "Usuario no encontrado"
                "ERROR_WRONG_PASSWORD" -> "Contraseña incorrecta"
                "ERROR_USER_DISABLED" -> "Este usuario ha sido deshabilitado"
                "ERROR_OPERATION_NOT_ALLOWED" -> "Operación no permitida"
                else -> e.message ?: "Error en el inicio de sesión"
            }
            Log.e(TAG, "❌ Error FirebaseAuthException en signIn: $errorMsg", e)
            throw Exception(errorMsg)
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error general en signIn: ${e.message}", e)
            throw Exception(e.message ?: "Error desconocido en el inicio de sesión")
        }
    }

    /**
     * Cierra la sesión del usuario actual
     */
    fun signOut() {
        try {
            Log.d(TAG, "🚪 Cerrando sesión")
            firebaseAuth.signOut()
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error en signOut: ${e.message}", e)
        }
    }

    /**
     * Obtiene el usuario actualmente autenticado
     */
    fun getCurrentUser() = firebaseAuth.currentUser

    /**
     * Obtiene el UID del usuario actual
     */
    fun getCurrentUserId(): String? = firebaseAuth.currentUser?.uid

    /**
     * Verifica si hay un usuario autenticado
     */
    fun isUserAuthenticated(): Boolean = firebaseAuth.currentUser != null

    /**
     * Envía un email de reset de contraseña
     */
    suspend fun sendPasswordResetEmail(email: String) {
        try {
            Log.d(TAG, "📧 Enviando email de reset a: $email")
            firebaseAuth.sendPasswordResetEmail(email).await()
            Log.d(TAG, "✅ Email de reset enviado")
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error al enviar email de reset: ${e.message}", e)
            throw Exception(e.message ?: "Error al enviar email de reset")
        }
    }
}

