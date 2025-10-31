package com.example.financeapp

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
import com.example.financeapp.ui.components.FinanceBottomBar
import com.example.financeapp.ui.navigation.NavGraph
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.poppinsFamily
import com.example.financeapp.ui.theme.screen.login.LoginScreen
import com.example.financeapp.ui.theme.screen.signup.SignUpScreen
import com.example.financeapp.ui.screen.LoginScreen
import com.example.financeapp.ui.screen.WelcomeScreen
import com.example.financeapp.ui.screen.transaction.TransactionScreen
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
                        modifier = Modifier.padding (innerPadding),
                        startDestination = "welcome",
                    ) {

                        composable(route = "welcome") {
                            WelcomeScreen(Modifier, navController, intent)
                        }
                        composable(route = "login") {
                            LoginScreen()
                        }

                        composable(route = "register") {
                            LoginScreen()
                        }
                        // Rutas para la bottom navigation
                        composable(route = "home") {
                            // TODO: Implementar Home Screen
                        }
                        composable(route = "analytics") {
                            // TODO: Implementar Analytics Screen
                        }
                        composable(route = "transfer") {
                            TransactionScreen(navController = navController)
                        }
                        composable(route = "layers") {
                            // TODO: Implementar Layers Screen
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

}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    FinanceAppTheme {
        LoginScreen ()
    }
}