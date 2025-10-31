package com.example.financeapp.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.screen.MainScreen
import com.example.financeapp.ui.screen.login.LoginScreen
import com.example.financeapp.ui.screen.login.SignUpScreen


private val bottomBarRoutes = listOf("home_tab", "analytics_tab", "transfer_tab", "layers_tab", "notifications_tab")
@Composable
fun NavGraph(startDestination: String = "login") {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                onLoginClick = {
                    navController.navigate("main_app") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onSignUpClick = { navController.navigate("signup") },
                onForgotPasswordClick = { navController.navigate("forgot_password") }
            )
        }

        composable("signup") {
            SignUpScreen(
                onSignUpClick = {
                    navController.navigate("main_app") {
                        popUpTo("signup") { inclusive = true }
                    }
                },
                onLoginClick = { navController.navigate("login") }
            )
        }

        /*composable("forgot_password") {
            ForgotPasswordScreen(
                onNextStepClick = { navController.navigate("security_pin") },
                onSignUpClick = { navController.navigate("signup") }
            )
        }*/

        /*composable("security_pin") {
            SecurityPinScreen(
                onContinueClick = {
                    navController.navigate("main_app") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }*/

        composable("main_app") {
            MainScreen()
        }
    }
}
