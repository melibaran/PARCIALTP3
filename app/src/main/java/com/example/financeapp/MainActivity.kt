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
import com.example.financeapp.ui.components.FinanceBottomBar
import com.example.financeapp.ui.navigation.NavGraph

import com.example.financeapp.ui.screen.transaction.TransactionDetailScreen
import com.example.financeapp.ui.screen.categories.CategoriesScreen
import com.example.financeapp.ui.screen.transaction.TransactionScreen
import com.example.financeapp.ui.screens.AccountBalanceScreen
import com.example.financeapp.ui.screens.ChatDetailScreen
import com.example.financeapp.ui.screens.HelpCenterScreen
import com.example.financeapp.ui.screens.HomeScreen
import com.example.financeapp.ui.screens.NotificationScreen
import com.example.financeapp.ui.screens.OnlineSupportScreen
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.poppinsFamily
import com.example.financeapp.ui.theme.screen.login.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            FinanceAppTheme {
                NavGraph()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    FinanceAppTheme {


    }
}