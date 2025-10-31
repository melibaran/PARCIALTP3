package com.example.financeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.components.FinanceBottomBar
import com.example.financeapp.ui.navigation.NavGraph
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.poppinsFamily
import com.example.financeapp.ui.theme.screen.login.LoginScreen
import com.example.financeapp.ui.theme.screen.signup.SignUpScreen
import com.example.financeapp.ui.screen.LoginScreen
import com.example.financeapp.ui.screen.WelcomeScreen
import com.example.financeapp.ui.screens.AccountBalanceScreen
import com.example.financeapp.ui.screens.FoodScreen
import com.example.financeapp.ui.screens.HomeScreen
import com.example.financeapp.ui.screen.transaction.TransactionScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            FinanceAppTheme {


                /*Scaffold(
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
                        composable(route = "layers") {
                            FoodScreen(navController = navController)
                        }
                        composable(route = "profile") {
                            // TODO: Implementar Profile Screen
                        }
                    }
                }*/
                NavGraph()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    androidx.compose.material3.Text(
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