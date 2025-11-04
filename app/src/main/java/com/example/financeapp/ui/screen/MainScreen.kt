package com.example.financeapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.FinanceBottomBar
import com.example.financeapp.ui.screen.categories.CategoriesScreen
import com.example.financeapp.ui.screen.changepin.ChangePinScreen
import com.example.financeapp.ui.screen.fingerprint.AddFingerprintScreen
import com.example.financeapp.ui.screen.fingerprint.FingerprintDetailScreen
import com.example.financeapp.ui.screen.fingerprint.FingerprintScreen
import com.example.financeapp.ui.screen.settings.DeleteAccountScreen
import com.example.financeapp.ui.screen.settings.NotificationSettingsScreen1
import com.example.financeapp.ui.screen.settings.PasswordSettingsScreen
import com.example.financeapp.ui.screen.settings.SettingsScreen
import com.example.financeapp.ui.screen.transaction.TransactionDetailScreen
import com.example.financeapp.ui.screen.transaction.TransactionScreen
import com.example.financeapp.ui.screen.accountbalance.AccountBalanceScreen
import com.example.financeapp.ui.screen.chatdetail.ChatDetailScreen
import com.example.financeapp.ui.screen.helpcenter.HelpCenterScreen
import com.example.financeapp.ui.screen.home.HomeScreen
import com.example.financeapp.ui.screen.notification.NotificationScreen
import com.example.financeapp.ui.screen.onlinesupport.OnlineSupportScreen
// Profile/EditProfile/Security/Success are in the same package and don't need imports
import com.example.financeapp.ui.theme.Caribbean_green


@Composable
fun MainScreen(onLogout: () -> Unit = {}) {
    val navController = rememberNavController()
    Scaffold(
        containerColor = Caribbean_green,
        bottomBar = {
            FinanceBottomBar(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = "home",
        ) {
            // Rutas para la bottom navigation
            composable(route = "home") {
                HomeScreen(navController = navController)
            }
            composable(route = "analytics") {
                AccountBalanceScreen(navController = navController)
            }
            composable(route = "transfer") {
                TransactionScreen(navController = navController)
            }
            composable("transaction_details") {
                TransactionDetailScreen(
                    navController = navController,
                    onBackClick = { navController.navigateUp() },
                    transactions = listOf(),
                    totalBalance = 0.0,
                    totalIncome = 0.0,
                    totalExpense = 0.0
                )
            }
            composable(route = "layers") {
                CategoriesScreen(navController = navController)
            }
            composable(route = "notifications") {
                NotificationScreen(navController = navController)
            }
            composable(route = "help_center") {
                HelpCenterScreen(navController = navController)
            }
            composable(route = "online_support") {
                OnlineSupportScreen(navController = navController)
            }
            composable(route = "chat_detail/{chatId}") { backStackEntry ->
                val chatId = backStackEntry.arguments?.getString("chatId") ?: "1"
                ChatDetailScreen(navController = navController, chatId = chatId)
            }
            composable(route = "profile") {
                HelpCenterScreen(navController = navController)
            }
            composable("settings") {
                SettingsScreen(
                    navController = navController,
                    onBackClick = { navController.navigateUp() },
                    onNotificationClick = { navController.navigate("notification_settings") },
                    onPasswordClick = { navController.navigate("password_settings") },
                    onDeleteAccountClick = { navController.navigate("delete_account") }
                )
            }

            composable("notification_settings") {
                NotificationSettingsScreen1(
                    navController = navController,
                    onBackClick = { navController.navigateUp() },
                    onNotificationClick = {  }
                )
            }

            composable("password_settings") {
                PasswordSettingsScreen(
                    navController = navController,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("delete_account") {
                DeleteAccountScreen(
                    navController = navController,
                    onBackClick = { navController.navigateUp() },
                    onDeleteConfirmed = { /* TODO: manejar borrado aquí (ViewModel/API) */ },
                    onCancel = { navController.navigateUp() }
                )
            }
            composable(route = "chat_detail/{chatId}") { backStackEntry ->
                val chatId = backStackEntry.arguments?.getString("chatId") ?: "1"
                ChatDetailScreen(navController = navController, chatId = chatId)
            }

            composable("profile") {
                ProfileScreen(
                    onEditProfileClick = { navController.navigate("edit_profile") },
                    onSecurityClick = { navController.navigate("security") },
                    onSettingClick = {
                        navController.navigate("settings"){
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    onHelpClick = {
                        navController.navigate("help_center") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    onLogoutClick = {
                        // Delega al NavController raíz
                        onLogout()
                    }
                )
            }


            composable("edit_profile") {
                EditProfileScreen(
                    onBackClick = { navController.navigateUp() },
                    onUpdateClick = { }
                )
            }

            composable("security") {
                SecurityScreen(
                    onBackClick = { navController.navigateUp() },
                    onChangePinClick = { navController.navigate("change_pin") },
                    onFingerprintClick = { navController.navigate("fingerprint") },
                    onTermsClick = { navController.navigate("terms_and_conditions") }
                )
            }

            composable("terms_and_conditions") {
                TermsAndConditionsScreen(
                    onBackClick = { navController.navigateUp() },
                    onAcceptClick = {
                        navController.navigate("profile") {
                            popUpTo("profile") { inclusive = true }
                        }
                    }
                )
            }

            composable("fingerprint") {
                FingerprintScreen(
                    onBackClick = { navController.navigateUp() },
                    onFingerprintClick = { fingerprint ->
                        navController.navigate("fingerprint_detail/${fingerprint.id}/${fingerprint.name}")
                    },
                    onAddFingerprintClick = {
                        navController.navigate("add_fingerprint")
                    }
                )
            }

            composable("add_fingerprint") {
                AddFingerprintScreen(
                    onBackClick = { navController.navigateUp() },
                    onUseTouchIdClick = {
                        navController.navigate("fingerprint_added_success") {
                            popUpTo("add_fingerprint") { inclusive = true }
                        }
                    }
                )
            }

            composable("fingerprint_added_success") {
                SuccessScreen(
                    message = stringResource(R.string.fingerprint_added_successfully),
                    onComplete = {
                        navController.navigate("fingerprint") {
                            popUpTo("fingerprint") { inclusive = true }
                        }
                    }
                )
            }

            composable("fingerprint_detail/{fingerprintId}/{fingerprintName}") { backStackEntry ->
                val fingerprintName = backStackEntry.arguments?.getString("fingerprintName") ?: ""
                FingerprintDetailScreen(
                    fingerprintName = fingerprintName,
                    onBackClick = { navController.navigateUp() },
                    onDeleteClick = {
                        navController.navigate("fingerprint_deleted_success") {
                            popUpTo("fingerprint_detail/{fingerprintId}/{fingerprintName}") { inclusive = true }
                        }
                    }
                )
            }

            composable("fingerprint_deleted_success") {
                SuccessScreen(
                    message = stringResource(R.string.fingerprint_deleted_successfully),
                    onComplete = {
                        navController.navigate("fingerprint") {
                            popUpTo("fingerprint") { inclusive = true }
                        }
                    }
                )
            }

            composable("change_pin") {
                ChangePinScreen(
                    onBackClick = { navController.navigateUp() },
                    onPinChanged = {
                        navController.navigate("pin_success") {
                            popUpTo("change_pin") { inclusive = true }
                        }
                    }
                )
            }

            composable("pin_success") {
                SuccessScreen(
                    message = stringResource(R.string.pin_changed_successfully),
                    onComplete = {
                        navController.navigate("security") {
                            popUpTo("security") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
