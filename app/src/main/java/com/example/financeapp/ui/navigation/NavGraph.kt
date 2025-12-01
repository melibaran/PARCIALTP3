package com.example.financeapp.ui.navigation

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.R
import com.example.financeapp.ui.screen.MainScreen
import com.example.financeapp.ui.screen.SuccessScreen
import com.example.financeapp.ui.screen.login.ForgotPasswordScreen
import com.example.financeapp.ui.screen.login.LoginScreen
import com.example.financeapp.ui.screen.login.NewPasswordScreen
import com.example.financeapp.ui.screen.login.SecurityPinScreen
import com.example.financeapp.ui.screen.login.SignUpScreen
import com.example.financeapp.ui.screen.onBoarding.InicioFinWise
import com.example.financeapp.ui.screen.onBoarding.OnBoardingScreen
import com.example.financeapp.ui.screen.onBoarding.SuccessScreenInicio


@Composable
fun NavGraph(
    startDestination: String = "auth_check",
    authViewModel: AuthenticationViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.collectAsState()

    NavHost(navController = navController, startDestination = startDestination) {
        // Pantalla de verificación de autenticación
        composable(route = "auth_check") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (authState) {
                    is AuthState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is AuthState.Authenticated -> {
                        // Usuario ya autenticado, ir a la pantalla principal
                        navController.navigate("main_app") {
                            popUpTo("auth_check") { inclusive = true }
                        }
                    }
                    is AuthState.Unauthenticated -> {
                        // Usuario no autenticado, ir al onboarding
                        navController.navigate("inicio-pre") {
                            popUpTo("auth_check") { inclusive = true }
                        }
                    }
                }
            }
        }

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
                    // Después de registrarse exitosamente, ir directamente a main_app
                    navController.navigate("main_app") {
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
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("main_app") { inclusive = true }
                    }
                }
            )
        }


    }
}
