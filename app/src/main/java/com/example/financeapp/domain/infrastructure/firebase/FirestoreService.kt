package com.example.financeapp.domain.infrastructure.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import java.util.Date

/**
 * Modelo de datos de usuario para Firestore
 */
data class FirestoreUser(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

/**
 * Servicio para manejar la persistencia de datos en Firestore
 */
@Singleton
class FirestoreService @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val TAG = "FirestoreService"
    }

    /**
     * Guarda los datos básicos de un nuevo usuario en Firestore
     */
    suspend fun saveUser(
        uid: String,
        email: String,
        displayName: String
    ) {
        try {
            Log.d(TAG, "💾 Guardando usuario en Firestore - UID: $uid, Email: $email")

            val user = FirestoreUser(
                uid = uid,
                email = email,
                displayName = displayName,
                createdAt = Date(),
                updatedAt = Date()
            )

            firestore
                .collection(USERS_COLLECTION)
                .document(uid)
                .set(user)
                .await()

            Log.d(TAG, "✅ Usuario guardado exitosamente en Firestore")
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error al guardar usuario en Firestore: ${e.message}", e)
            throw Exception("Error al guardar usuario en Firestore: ${e.message}")
        }
    }

    /**
     * Obtiene los datos de un usuario desde Firestore
     */
    suspend fun getUser(uid: String): FirestoreUser? {
        return try {
            Log.d(TAG, "📖 Obteniendo usuario de Firestore - UID: $uid")

            val document = firestore
                .collection(USERS_COLLECTION)
                .document(uid)
                .get()
                .await()

            val user = document.toObject(FirestoreUser::class.java)
            Log.d(TAG, "✅ Usuario obtenido de Firestore: ${user?.email}")
            user
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error al obtener usuario de Firestore: ${e.message}", e)
            throw Exception("Error al obtener usuario de Firestore: ${e.message}")
        }
    }

    /**
     * Actualiza los datos de un usuario en Firestore
     */
    suspend fun updateUser(uid: String, updates: Map<String, Any>) {
        try {
            Log.d(TAG, "🔄 Actualizando usuario en Firestore - UID: $uid")

            firestore
                .collection(USERS_COLLECTION)
                .document(uid)
                .update(updates + ("updatedAt" to Date()))
                .await()

            Log.d(TAG, "✅ Usuario actualizado en Firestore")
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error al actualizar usuario en Firestore: ${e.message}", e)
            throw Exception("Error al actualizar usuario en Firestore: ${e.message}")
        }
    }

    /**
     * Elimina un usuario de Firestore
     */
    suspend fun deleteUser(uid: String) {
        try {
            Log.d(TAG, "🗑️ Eliminando usuario de Firestore - UID: $uid")

            firestore
                .collection(USERS_COLLECTION)
                .document(uid)
                .delete()
                .await()

            Log.d(TAG, "✅ Usuario eliminado de Firestore")
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error al eliminar usuario de Firestore: ${e.message}", e)
            throw Exception("Error al eliminar usuario de Firestore: ${e.message}")
        }
    }

    /**
     * Verifica si un usuario existe en Firestore
     */
    suspend fun userExists(uid: String): Boolean {
        return try {
            Log.d(TAG, "🔍 Verificando existencia de usuario - UID: $uid")

            val document = firestore
                .collection(USERS_COLLECTION)
                .document(uid)
                .get()
                .await()

            val exists = document.exists()
            Log.d(TAG, "✅ Usuario existe: $exists")
            exists
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error al verificar existencia: ${e.message}", e)
            false
        }
    }
}

