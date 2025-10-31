package com.example.financeapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.components.FinanceBottomBar
import com.example.financeapp.ui.screen.categories.CategoriesScreen
import com.example.financeapp.ui.screen.transaction.TransactionDetailScreen
import com.example.financeapp.ui.screen.transaction.TransactionScreen
import com.example.financeapp.ui.screens.AccountBalanceScreen
import com.example.financeapp.ui.screens.ChatDetailScreen
import com.example.financeapp.ui.screens.HelpCenterScreen
import com.example.financeapp.ui.screens.HomeScreen
import com.example.financeapp.ui.screens.NotificationScreen
import com.example.financeapp.ui.screens.OnlineSupportScreen
import com.example.financeapp.ui.theme.Caribbean_green

@Composable
fun MainScreen() {
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
        }
    }
}

