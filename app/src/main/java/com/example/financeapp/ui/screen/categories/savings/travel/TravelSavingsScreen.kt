package com.example.financeapp.ui.screen.categories.savings.travel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.ui.screen.categories.savings.Deposit
import com.example.financeapp.ui.screen.categories.savings.SavingsDetailScreen
import com.example.financeapp.ui.screen.categories.savings.SavingsViewModel

@Composable
fun TravelSavingsScreen(
    navController: NavController,
    viewModel: SavingsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val existingGoal = uiState.savingsGoals.find { it.title == "Travel" }

    LaunchedEffect(existingGoal) {
        existingGoal?.let { goal ->
            val travelGoalWithDeposits = goal.copy(
                deposits = mapOf(
                    "April" to listOf(
                        Deposit("Travel Deposit", "19:56", "April 30", 217.77),
                        Deposit("Travel Deposit", "17:42", "April 14", 217.77),
                        Deposit("Travel Deposit", "12:30", "April 02", 217.77)
                    )
                )
            )
            viewModel.updateSavingsGoal(travelGoalWithDeposits)
        }
    }

    SavingsDetailScreen(
        navController = navController,
        goalTitle = "Travel",
        viewModel = viewModel
    )
}
