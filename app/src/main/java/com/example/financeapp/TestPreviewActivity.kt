package com.example.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.screens.AccountBalanceScreen
import com.example.financeapp.ui.screen.categories.food.FoodScreen
import com.example.financeapp.ui.screens.HomeScreen
import com.example.financeapp.ui.theme.FinanceAppTheme

class TestPreviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var selected by remember { mutableStateOf(0) }

            FinanceAppTheme {
                Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = { selected = 0 }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
                            Text(text = "Home")
                        }
                        Button(onClick = { selected = 1 }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
                            Text(text = "Account")
                        }
                        Button(onClick = { selected = 2 }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
                            Text(text = "Food")
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    when (selected) {
                        0 -> HomeScreen(navController)
                        1 -> AccountBalanceScreen(navController)
                        2 -> FoodScreen(navController)
                    }
                }
            }
        }
    }
}
