package com.example.financeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.theme.screen.forgotpassword.ForgotPasswordScreen
import com.example.financeapp.ui.theme.screen.login.LoginScreen
import com.example.financeapp.ui.theme.screen.securitypin.SecurityPinScreen
import com.example.financeapp.ui.theme.screen.signup.SignUpScreen

@Composable
fun NavGraph(startDestination: String = "login") {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                onLoginClick = { /* navegar a home o manejar login */ },
                onSignUpClick = { navController.navigate("signup") },
                onForgotPasswordClick = { navController.navigate("forgot_password") }
            )
        }

        composable("signup") {
            SignUpScreen(
                onSignUpClick = { /* Lógica de registro */ },
                onLoginClick = { navController.navigate("login") }
            )
        }

        composable("forgot_password") {
            ForgotPasswordScreen(
                onNextStepClick = { navController.navigate("security_pin") },
                onSignUpClick = { navController.navigate("signup") }
            )
        }

        composable("security_pin") {
            SecurityPinScreen(
                onContinueClick = { /* Lógica para continuar */ },
                onSignUpClick =  { navController.navigate("signup") }
            )
        }
    }
}
