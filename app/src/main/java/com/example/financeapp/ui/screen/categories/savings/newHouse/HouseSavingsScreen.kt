package com.example.financeapp.ui.screen.categories.savings.newHouse

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
fun HouseSavingsScreen(
    navController: NavController,
    viewModel: SavingsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val existingGoal = uiState.savingsGoals.find { it.title == "New House" }

    LaunchedEffect(existingGoal) {
        existingGoal?.let { goal ->
            val houseGoalWithDeposits = goal.copy(
                deposits = mapOf(
                    "April" to listOf(
                        Deposit("House Deposit", "19:56", "April 5", 477.77)
                    ),
                    "January" to listOf(
                        Deposit("House Deposit", "20:25", "January 16", 102.67),
                        Deposit("House Deposit", "15:56", "January 02", 45.04)
                    )
                )
            )
            viewModel.updateSavingsGoal(houseGoalWithDeposits)
        }
    }

    SavingsDetailScreen(
        navController = navController,
        goalTitle = "New House",
        viewModel = viewModel
    )
}
