package com.example.financeapp

import android.app.Application
import android.util.Log
import com.example.financeapp.BuildConfig
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class para la app de Finanzas
 * Inicializa Hilt y configura Firebase
 *
 * Nota: Los errores de Google Play Services son normales en emuladores
 * sin Google Play Services completos. No afectan la funcionalidad de Firebase.
 */
@HiltAndroidApp
class FinanceApplication : Application() {

    companion object {
        private const val TAG = "FinanceApp"
    }

    override fun onCreate() {
        super.onCreate()

        initializeFirebase()
    }

    private fun initializeFirebase() {
        try {
            // En desarrollo, conectar a emuladores locales
            if (BuildConfig.DEBUG) {
                try {
                    // Conectar a Firebase Auth Emulator (puerto 9099)
                    Firebase.auth.useEmulator("10.0.2.2", 9099)
                    Log.i(TAG, "✅ Firebase Auth Emulator conectado en puerto 9099")
                } catch (e: Exception) {
                    Log.w(TAG, "No se pudo conectar a Auth Emulator: ${e.message}")
                }

                try {
                    // Conectar a Firestore Emulator (puerto 8080)
                    Firebase.firestore.useEmulator("10.0.2.2", 8080)
                    Log.i(TAG, "✅ Firestore Emulator conectado en puerto 8080")
                } catch (e: Exception) {
                    Log.w(TAG, "No se pudo conectar a Firestore Emulator: ${e.message}")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error en inicialización de Firebase: ${e.message}", e)
        }
    }
}

