package com.example.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.financeapp.ui.navigation.NavGraph
import com.example.financeapp.ui.screen.login.LoginScreen
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.LocalThemeController
import com.example.financeapp.ui.theme.ThemeController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            var isDarkTheme by rememberSaveable { mutableStateOf(false) }
            val themeController = ThemeController(
                isDarkMode = isDarkTheme,
                toggleDarkMode = { isDarkTheme = it }
            )

            CompositionLocalProvider(LocalThemeController provides themeController) {
                FinanceAppTheme(darkTheme = isDarkTheme, dynamicColor = false) {
                    NavGraph()
                }
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