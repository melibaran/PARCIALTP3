package com.example.financeapp.ui.screen.categories.savings.wedding

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
fun WeddingSavingsScreen(
    navController: NavController,
    viewModel: SavingsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val existingGoal = uiState.savingsGoals.find { it.title == "Wedding" }

    LaunchedEffect(existingGoal) {
        existingGoal?.let { goal ->
            val weddingGoalWithDeposits = goal.copy(
                deposits = mapOf(
                    "November" to listOf(
                        Deposit("Wedding Deposit", "18:46", "November 15", 87.32)
                    ),
                    "September" to listOf(
                        Deposit("Wedding Deposit", "21:45", "September 30", 22.99),
                        Deposit("Wedding Deposit", "12:25", "September 15", 185.94)
                    )
                )
            )
            viewModel.updateSavingsGoal(weddingGoalWithDeposits)
        }
    }

    SavingsDetailScreen(
        navController = navController,
        goalTitle = "Wedding",
        viewModel = viewModel
    )
}