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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.poppinsFamily
import com.example.financeapp.ui.theme.screen.LoginScreen
import com.example.financeapp.ui.theme.screen.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

       val intent = Intent(DashboardActivity::class.toString())


        setContent {
            val navController = rememberNavController()

            FinanceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
                    }
                    Greeting(
                        name = "Melissa",
                        modifier = Modifier.padding(innerPadding)
                    )
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
        Greeting("Melissa")
    }
}