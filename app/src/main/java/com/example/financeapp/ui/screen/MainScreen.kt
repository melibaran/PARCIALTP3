package com.example.financeapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.financeapp.ui.screen.categories.savings.AddExpensesSaving
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.R
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.components.FinanceBottomBar
import com.example.financeapp.ui.screen.categories.AddExpensesScreen
import com.example.financeapp.ui.screen.categories.CategoriesScreen
import com.example.financeapp.ui.screen.categories.food.FoodScreen
import com.example.financeapp.ui.screen.categories.transport.TransportScreen
import com.example.financeapp.ui.screen.categories.medicine.MedicineScreen
import com.example.financeapp.ui.screen.categories.groceries.GroceriesScreen
import com.example.financeapp.ui.screen.categories.rent.RentScreen
import com.example.financeapp.ui.screen.categories.gift.GiftScreen
import com.example.financeapp.ui.screen.categories.entertainment.EntertainmentScreen
import com.example.financeapp.ui.screen.profile.changepin.ChangePinScreen
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.ui.screen.profile.fingerprint.AddFingerprintScreen
import com.example.financeapp.ui.screen.profile.fingerprint.FingerprintDetailScreen
import com.example.financeapp.ui.screen.profile.fingerprint.FingerprintScreen
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
import com.example.financeapp.ui.screen.profile.EditProfileScreen
import com.example.financeapp.ui.screen.profile.ProfileScreen
import com.example.financeapp.ui.screen.profile.SecurityScreen
import com.example.financeapp.ui.screen.profile.TermsAndConditionsScreen


import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun MainScreen(onLogout: () -> Unit = {}) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Scaffold(
        topBar = {

        },
        bottomBar = {
            FinanceBottomBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) {
        NavHost(
            navController = navController,
            modifier = Modifier.padding(0.dp),
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

            // Rutas de categorías
            composable("food") {
                FoodScreen(navController = navController)
            }
            composable("transport") {
                TransportScreen(navController = navController)
            }
            composable("medicine") {
                MedicineScreen(navController = navController)
            }
            composable("groceries") {
                GroceriesScreen(navController = navController)
            }
            composable("rent") {
                RentScreen(navController = navController)
            }
            composable("gift") {
                GiftScreen(navController = navController)
            }
            composable("entertainment") {
                EntertainmentScreen(navController = navController)
            }

            // Ruta principal de Savings
            composable("savings") { backStackEntry ->
                com.example.financeapp.ui.screen.savings.SavingsScreen(
                    navController = navController,
                    viewModel = hiltViewModel(backStackEntry)
                )
            }

            // Rutas internas de Savings (comparten el mismo ViewModel)
            composable("car_savings") { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("savings")
                }
                com.example.financeapp.ui.screen.categories.savings.car.CarSavingsScreen(
                    navController = navController,
                    viewModel = hiltViewModel(parentEntry)
                )
            }

            composable("house_savings") { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("savings")
                }
                com.example.financeapp.ui.screen.categories.savings.newHouse.HouseSavingsScreen(
                    navController = navController,
                    viewModel = hiltViewModel(parentEntry)
                )
            }

            composable("travel_savings") { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("savings")
                }
                com.example.financeapp.ui.screen.categories.savings.travel.TravelSavingsScreen(
                    navController = navController,
                    viewModel = hiltViewModel(parentEntry)
                )
            }

            composable("wedding_savings") { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("savings")
                }
                com.example.financeapp.ui.screen.categories.savings.wedding.WeddingSavingsScreen(
                    navController = navController,
                    viewModel = hiltViewModel(parentEntry)
                )
            }

            composable("add_expenses") {
                AddExpensesScreen(navController = navController)
            }

            composable("add_savings"){
                AddExpensesSaving(navController = navController)
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
                ProfileScreen(
                    onEditProfileClick = { navController.navigate("edit_profile") },
                    onSecurityClick = { navController.navigate("security") },
                    onSettingClick = {
                        navController.navigate("settings") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    onHelpClick = {
                        navController.navigate("help_center") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    onLogoutClick = {
                        onLogout()
                    },
                    onNotificationsClick = { navController.navigate("notifications") }
                )
            }
            composable("settings") {
                SettingsScreen(
                    navController = navController,
                    onPasswordClick = { navController.navigate("password_settings") },
                    onDeleteAccountClick = { navController.navigate("delete_account") }
                )
            }

            composable("notification_settings") {
                NotificationSettingsScreen1(
                    navController = navController
                )
            }

            composable("password_settings") {
                PasswordSettingsScreen(
                    navController = navController,
                    onBackClick = { navController.navigateUp() }
                )
            }

            composable("password_success") {
                SuccessScreen(
                    message = stringResource(R.string.password_succesful),
                    onComplete = {
                        navController.navigate("settings") {
                            popUpTo("password_success") { inclusive = true }
                        }
                    }
                )
            }

            composable("delete_account") {
                DeleteAccountScreen(
                    navController = navController,
                    onBackClick = { navController.navigateUp() },
                    onDeleteConfirmed = { password ->
                        onLogout()
                    },
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
                        navController.navigate("settings") {
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
                    },
                    onNotificationsClick = { navController.navigate("notifications") }
                )
            }


            composable("edit_profile") {
                EditProfileScreen(
                    onBackClick = { navController.navigateUp() },
                    onUpdateClick = { navController.navigate("profile") },
                    onNotificationsClick = { navController.navigate("notifications") }
                )
            }

            composable("security") {
                SecurityScreen(
                    onBackClick = { navController.navigateUp() },
                    onChangePinClick = { navController.navigate("change_pin") },
                    onFingerprintClick = { navController.navigate("fingerprint") },
                    onTermsClick = { navController.navigate("terms_and_conditions") },
                    onNotificationsClick = { navController.navigate("notifications") }
                )
            }

            composable("terms_and_conditions") {
                TermsAndConditionsScreen(
                    onBackClick = { navController.navigateUp() },
                    onAcceptClick = {
                        navController.navigate("profile") {
                            popUpTo("profile") { inclusive = true }
                        }
                    },
                    onNotificationsClick = { navController.navigate("notifications") }
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
                    },
                    onNotificationsClick = { navController.navigate("notifications") }
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
                            popUpTo("fingerprint_detail/{fingerprintId}/{fingerprintName}") {
                                inclusive = true
                            }
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
                    },
                    onNotificationsClick = { navController.navigate("notifications") }
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