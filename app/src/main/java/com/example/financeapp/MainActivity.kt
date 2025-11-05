package com.example.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.financeapp.ui.navigation.NavGraph

import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.DarkModeState
import com.example.financeapp.ui.screen.login.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            // Estado del dark mode a nivel de actividad para que persista
            val darkModeState = remember { mutableStateOf(false) }
            val darkModeStateValue = DarkModeState(
                isDarkMode = darkModeState.value,
                setDarkMode = { darkModeState.value = it }
            )
            
            FinanceAppTheme(darkModeState = darkModeStateValue) {
                NavGraph()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    FinanceAppTheme {
        LoginScreen(onLoginClick = {}, onSignUpClick = {}, onForgotPasswordClick = {})
    }
}