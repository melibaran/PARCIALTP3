package com.example.financeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.R
import com.example.financeapp.ui.screen.MainScreen
import com.example.financeapp.ui.screen.SuccessScreen
import com.example.financeapp.ui.screen.login.ForgotPasswordScreen
import com.example.financeapp.ui.screen.login.NewPasswordScreen
import com.example.financeapp.ui.screen.login.LoginScreen
import com.example.financeapp.ui.screen.login.SecurityPinScreen
import com.example.financeapp.ui.screen.login.SignUpScreen
import com.example.financeapp.ui.screen.onBoarding.InicioFinWise
import com.example.financeapp.ui.screen.onBoarding.OnBoardingScreen
import com.example.financeapp.ui.screen.onBoarding.SuccessScreenInicio
import com.example.financeapp.ui.screen.accountbalance.AccountBalanceScreen
import com.example.financeapp.ui.screen.chatdetail.ChatDetailScreen
import com.example.financeapp.ui.screen.helpcenter.HelpCenterScreen
import com.example.financeapp.ui.screen.home.HomeScreen
import com.example.financeapp.ui.screen.notification.NotificationScreen
import com.example.financeapp.ui.screen.onlinesupport.OnlineSupportScreen


private val bottomBarRoutes = listOf("home_tab", "analytics_tab", "transfer_tab", "layers_tab", "notifications_tab")
@Composable
fun NavGraph(startDestination: String = "inicio-pre") {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route= "inicio-pre"){
            SuccessScreenInicio("FinWise",
                onComplete = {navController.navigate("inicio") {
                    popUpTo("inicio-pre") { inclusive = true }
                }
                })
        }
        composable(route= "inicio"){
            InicioFinWise( onLoginClick = {
                navController.navigate("onboarding") {
                    popUpTo("inicio") { inclusive = true }
                }
            },
                onSignUpClick = { navController.navigate("onboarding") },
                onForgotPasswordClick = { navController.navigate("onBoarding")}
            )
        }
        composable (route = "onboarding") {
            OnBoardingScreen(title = " Welcome to \n Expense Manager", imageRes = R.drawable.ilustracion_mano,
                onNext = { navController.navigate("onboarding1")}
            )
        }
        composable(route = "onboarding1") {
            OnBoardingScreen(title = "¿Are You Ready To\n Take Control Of \nYour Finaces?", imageRes = R.drawable.card_mobile,
                onNext = { navController.navigate("login")}
            )
        }
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
                    navController.navigate("login") {
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
            SuccessScreen(message = "Password Has Been \nChanged Successfully",
                onComplete = {navController.navigate("login") {
                    popUpTo("sceen_newPassword") { inclusive = true }
                } })
        }

        composable("main_app") {
            MainScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("main_app") { inclusive = true }
                    }
                }
            )
        }


    }
}
