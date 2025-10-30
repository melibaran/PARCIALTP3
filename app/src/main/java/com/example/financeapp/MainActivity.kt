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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.components.FinanceBottomBar
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.poppinsFamily
import com.example.financeapp.ui.theme.screen.LoginScreen
import com.example.financeapp.ui.theme.screen.WelcomeScreen
import com.example.financeapp.ui.screens.AccountBalanceScreen
import com.example.financeapp.ui.screens.FoodScreen
import com.example.financeapp.ui.screens.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

/*        val intent = Intent(DashboardActivity::class.toString())*/


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
                        modifier = Modifier.padding (innerPadding),
                        startDestination = "welcome",
                    ) {

                        composable(route = "welcome") {
                            WelcomeScreen(Modifier, navController, intent)
                        }
                        composable(route = "login") {
                            LoginScreen(Modifier, navController)
                        }

                        composable(route = "register") {
                            LoginScreen(Modifier, navController)
                        }
                        // Rutas para la bottom navigation
                        composable(route = "home") {
                            HomeScreen(navController = navController)
                        }
                        composable(route = "analytics") {
                            AccountBalanceScreen(navController = navController)
                        }
                        composable(route = "transfer") {
                            // TODO: Implementar Transfer Screen
                        }
                        composable(route = "layers") {
                            FoodScreen(navController = navController)
                        }
                        composable(route = "profile") {
                            // TODO: Implementar Profile Screen
                        }
                    }
                }
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
fun GreetingPreview() {
    FinanceAppTheme {
        Greeting("Android")
    }
}