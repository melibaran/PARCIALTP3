package com.example.financeapp.presentation.screen
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun WelcomeScreen(modifier: Modifier, navController: NavHostController, intent: Intent) {



}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    val navController= rememberNavController()
    WelcomeScreen (Modifier.fillMaxSize(), navController, Intent(""))
}

