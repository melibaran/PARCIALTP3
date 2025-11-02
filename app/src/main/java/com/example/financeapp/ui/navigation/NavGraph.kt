package com.example.financeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.screens.HomeScreen
import com.example.financeapp.ui.screen.settings.DeleteAccountScreen
import com.example.financeapp.ui.screen.ProfileScreen
import com.example.financeapp.ui.screen.EditProfileScreen
import com.example.financeapp.ui.screen.settings.NotificationSettingsScreen1
import com.example.financeapp.ui.screen.MainScreen
import com.example.financeapp.ui.screen.login.SignUpScreen
import com.example.financeapp.ui.screen.login.ForgotPasswordScreen
import com.example.financeapp.ui.screen.login.NewPasswordScreen
import com.example.financeapp.ui.screen.login.PasswordChangedScreen
import com.example.financeapp.ui.theme.screen.login.LoginScreen
import com.example.financeapp.ui.screen.settings.PasswordSettingsScreen
import com.example.financeapp.ui.screen.settings.SettingsScreen
import com.example.financeapp.ui.screen.login.SecurityPinScreen


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
                    navController.navigate("forgot_password") {
                        popUpTo("signup") { inclusive = true }
                    }
                },
                onLoginClick = { navController.navigate("login") }
            )
        }

        composable("forgot_password") {
            ForgotPasswordScreen(
                onNextStepClick = {
                    navController.navigate("security_pin"){
                    popUpTo("forgot_password") { inclusive = true }}},
                onSignUpClick = { navController.navigate("signup") }
            )
        }

        composable("security_pin") {
            SecurityPinScreen(
                onContinueClick = {
                    navController.navigate("new_password") {
                        popUpTo("security_pin") { inclusive = true }
                    }
                }
            )
        }
        composable(route = "new_password") {
            NewPasswordScreen(
                onChangeClick = {
                    navController.navigate("screen_newPassword") {
                        popUpTo("new_password") { inclusive = true }
                    }
                }
            )
        }

        composable(route = "screen_newPassword") {
            PasswordChangedScreen()
        }



        composable("main_app") {
            MainScreen()
        }
    }
}



