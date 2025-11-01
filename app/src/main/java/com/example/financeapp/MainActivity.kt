package com.example.financeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.components.FinanceBottomBar
import com.example.financeapp.ui.navigation.NavGraph
import com.example.financeapp.ui.screen.changepin.ChangePinScreen
import com.example.financeapp.ui.screen.fingerprint.FingerprintScreen
import com.example.financeapp.ui.screen.EditProfileScreen
import com.example.financeapp.ui.screen.LoginScreen
import com.example.financeapp.ui.screen.ProfileScreen
import com.example.financeapp.ui.screen.SecurityScreen
import com.example.financeapp.ui.screen.SuccessScreen
import com.example.financeapp.ui.screen.WelcomeScreen
import com.example.financeapp.ui.screen.transaction.TransactionDetailScreen
import com.example.financeapp.ui.screen.categories.CategoriesScreen
import com.example.financeapp.ui.screen.categories.AddExpensesScreen
import com.example.financeapp.ui.screen.categories.food.FoodScreen
import com.example.financeapp.ui.screen.categories.transport.TransportScreen
import com.example.financeapp.ui.screen.categories.medicine.MedicineScreen
import com.example.financeapp.ui.screen.categories.groceries.GroceriesScreen
import com.example.financeapp.ui.screen.categories.rent.RentScreen
import com.example.financeapp.ui.screen.categories.gift.GiftScreen
import com.example.financeapp.ui.screen.categories.entertainment.EntertainmentScreen
import com.example.financeapp.ui.screen.settings.NotificationSettingsScreen1
import com.example.financeapp.ui.screen.transaction.TransactionScreen
import com.example.financeapp.ui.screen.settings.SettingsScreen
import com.example.financeapp.ui.screen.settings.NotificationSettingsScreen1
import com.example.financeapp.ui.screen.transaction.TransactionScreen
import com.example.financeapp.ui.screens.AccountBalanceScreen
import com.example.financeapp.ui.screens.ChatDetailScreen
import com.example.financeapp.ui.screens.HelpCenterScreen
import com.example.financeapp.ui.screens.HomeScreen
import com.example.financeapp.ui.screens.NotificationScreen
import com.example.financeapp.ui.screens.OnlineSupportScreen
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.poppinsFamily
import com.example.financeapp.ui.theme.screen.login.LoginScreen
import com.example.financeapp.ui.screen.settings.PasswordSettingsScreen
import com.example.financeapp.ui.screen.settings.SettingsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            FinanceAppTheme {


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
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

                        composable(route = "welcome") {
                            // WelcomeScreen expects an Intent parameter; pass a simple Intent
                            WelcomeScreen(Modifier.fillMaxSize(), navController, Intent())
                        }
                        composable(route = "login") {
                            LoginScreen(Modifier.fillMaxSize(), navController)
                        }

                        composable(route = "register") {
                            LoginScreen(Modifier.fillMaxSize(), navController)
                        }
                        // Rutas para la bottom navigation
                        composable(route = "home") {
                            HomeScreen(navController = navController)
                        }
                        composable(route = "analytics") {
                            AccountBalanceScreen(navController = navController)
                        }
                        composable(route = "transfer") {
                            // Using existing TransactionScreen
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
                        composable(route = "food") {
                            FoodScreen(navController = navController)
                        }
                        composable(route = "transport") {
                            TransportScreen(navController = navController)
                        }
                        composable(route = "medicine") {
                            MedicineScreen(navController = navController)
                        }
                        composable(route = "groceries") {
                            GroceriesScreen(navController = navController)
                        }
                        composable(route = "rent") {
                            RentScreen(navController = navController)
                        }
                        composable(route = "gift") {
                            GiftScreen(navController = navController)
                        }
                        composable(route = "entertainment") {
                            EntertainmentScreen(navController = navController)
                        }
                        composable(route = "add_expenses") {
                            AddExpensesScreen(navController = navController)
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
                        composable(route = "settings") {
                            SettingsScreen(
                                navController = navController,
                                onBackClick = { navController.navigateUp() }
                            )
                        }
                        composable(route = "notification_settings") {
                            NotificationSettingsScreen1(
                                navController = navController,
                                onBackClick = { navController.navigateUp() },
                            )
                        }
                        composable(route = "password_settings") {
                            PasswordSettingsScreen(
                                navController = navController,
                                onBackClick = { navController.navigateUp() },
                                onNotificationClick = { /* TODO */ }
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
                                    navController.navigate("login") {
                                        popUpTo("home") { inclusive = true }
                                    }
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
                                onTermsClick = { }
                            )
                        }
                        
                        composable("fingerprint") {
                            FingerprintScreen(
                                onBackClick = { navController.navigateUp() }
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
                //NavGraph()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", fontFamily = poppinsFamily,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    FinanceAppTheme {
        LoginScreen ()
    }
}