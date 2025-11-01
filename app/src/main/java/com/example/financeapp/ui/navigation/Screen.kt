package com.example.financeapp.ui.navigation

/**
 * Sealed class que define todas las rutas de navegación de la app
 * Patrón: Type-Safe Navigation
 *
 * Ventajas:
 * - Type safety en tiempo de compilación
 * - Autocomplete del IDE
 * - Fácil refactorización
 * - Centralización de rutas
 */
sealed class Screen(val route: String) {
    // Auth
    data object Welcome : Screen("welcome")
    data object Login : Screen("login")
    data object Success : Screen("success")

    // Main Navigation
    data object Home : Screen("home")
    data object AccountBalance : Screen("account_balance")
    data object HelpCenter : Screen("help_center")
    data object Profile : Screen("profile")

    // Categories
    data object Categories : Screen("categories")
    data object Food : Screen("food")
    data object Transport : Screen("transport")
    data object Groceries : Screen("groceries")
    data object Medicine : Screen("medicine")
    data object Rent : Screen("rent")
    data object Gift : Screen("gift")
    data object Entertainment : Screen("entertainment")
    data object AddExpenses : Screen("add_expenses")

    // Transactions
    data object Transaction : Screen("transaction")
    data object TransactionDetails : Screen("transaction_details")

    // Settings
    data object Settings : Screen("settings")
    data object Security : Screen("security")
    data object EditProfile : Screen("edit_profile")
    data object ChangePin : Screen("change_pin")
    data object Fingerprint : Screen("fingerprint")
    data object FingerprintDetail : Screen("fingerprint_detail")
    data object NotificationSettings : Screen("notification_settings")
    data object DeleteAccount : Screen("delete_account")
    data object TermsAndConditions : Screen("terms")

    // Notifications
    data object Notifications : Screen("notifications")

    // Chat
    data object ChatDetail : Screen("chat_detail")
}

/**
 * Extension function para navegar de forma segura
 */
fun androidx.navigation.NavController.navigateSafe(screen: Screen) {
    navigate(screen.route) {
        launchSingleTop = true
        restoreState = true
    }
}

