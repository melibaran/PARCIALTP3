package com.example.financeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.screens.HomeScreen
import com.example.financeapp.ui.screen.ProfileScreen
import com.example.financeapp.ui.screen.settings.NotificationSettingsScreen1
import com.example.financeapp.ui.theme.screen.login.LoginScreen
import com.example.financeapp.ui.theme.screen.signup.SignUpScreen
import com.example.financeapp.ui.screen.settings.PasswordSettingsScreen
import com.example.financeapp.ui.screen.settings.SettingsScreen

@Composable
fun NavGraph(startDestination: String = "login") {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                onLoginClick = { navController.navigate("home") },
                onSignUpClick = { navController.navigate("signup") },
                onForgotPasswordClick = { navController.navigate(route = "forgotpassword") }
            )
        }

        composable("signup") {
            SignUpScreen(
                onLoginClick = { navController.navigate("login") }
            )
        }
        composable(route = "forgotpassword"){
             /*ForgotPasswordScreen(
                 onSignUpClick = { navController.navigate("signup") },

             )*/
        }
        composable("home") {
            HomeScreen(
                navController = navController
            )
        }

        composable("settings") {
            SettingsScreen(
                navController = navController,
                onBackClick = { navController.navigateUp() },
                onNotificationClick = { navController.navigate("notification_settings") },
                onPasswordClick = { navController.navigate("password_settings") },
                onDeleteAccountClick = { }
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
                onBackClick = { navController.navigateUp() },
                onNotificationClick = { navController.navigate("password_settings") },
                navController = navController
            )
        }

//      composable("profile") {
//                            ProfileScreen(
//                                onEditProfileClick = { /* TODO: Navegar a editar perfil */ },
//                                onSecurityClick = { /* TODO: Navegar a seguridad */ },
//                                onSettingClick = { /* TODO: Navegar a configuración */ },
//                                onHelpClick = {
//                                    navController.navigate("help_center") {
//                                        popUpTo("home") { inclusive = true }
//                                    }
//                                },
//                                onLogoutClick = { navController.navigate("login") {
//                                    popUpTo("home") { inclusive = true }
//                                } }
//                            )
//                        }
    }
}
