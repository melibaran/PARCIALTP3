package com.example.financeapp.ui.utils

/**
 * Constantes de la aplicación
 * Patrón: Constants Object
 *
 * Ventaja: Evita hardcoded strings y facilita cambios
 */
object Constants {
    // Navigation
    const val MAIN_GRAPH = "main_graph"
    const val AUTH_GRAPH = "auth_graph"

    // Shared Preferences Keys
    const val PREF_USER_ID = "user_id"
    const val PREF_IS_LOGGED_IN = "is_logged_in"
    const val PREF_USE_FINGERPRINT = "use_fingerprint"

    // Database
    const val DATABASE_NAME = "finance_app_database"
    const val DATABASE_VERSION = 1

    // API
    const val BASE_URL = "https://api.financeapp.com/"
    const val TIMEOUT_SECONDS = 30L

    // Validation
    const val MIN_PIN_LENGTH = 4
    const val MAX_PIN_LENGTH = 6
    const val MIN_PASSWORD_LENGTH = 8

    // Formats
    const val DATE_FORMAT = "dd/MM/yyyy"
    const val TIME_FORMAT = "HH:mm"
    const val DATETIME_FORMAT = "HH:mm - MMMM dd"

    // Limits
    const val MAX_TRANSACTIONS_PER_PAGE = 20
    const val MAX_CATEGORIES = 10
}

/**
 * Extension functions para validaciones
 */
object Validators {
    fun String.isValidEmail(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun String.isValidPin(): Boolean {
        return this.length in Constants.MIN_PIN_LENGTH..Constants.MAX_PIN_LENGTH
            && this.all { it.isDigit() }
    }

    fun String.isValidPassword(): Boolean {
        return this.length >= Constants.MIN_PASSWORD_LENGTH
            && this.any { it.isDigit() }
            && this.any { it.isUpperCase() }
    }

    fun Double.formatCurrency(): String {
        return "$${"%.2f".format(this)}"
    }
}

