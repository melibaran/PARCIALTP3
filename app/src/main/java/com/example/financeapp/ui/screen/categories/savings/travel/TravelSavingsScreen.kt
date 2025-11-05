package com.example.financeapp.ui.screen.categories.savings.travel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryAddSavingsButton
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryDesign
import com.example.financeapp.ui.screen.categories.arquitectura.SavingsDepositList
import com.example.financeapp.ui.screen.categories.arquitectura.SavingsGoalHeader
import com.example.financeapp.ui.screen.categories.savings.Deposit
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

    val goal = existingGoal ?: return

    CategoryDesign(
        title = "Travel",
        content = {
            SavingsGoalHeader(
                iconId = goal.iconId,
                title = goal.title,
                savedAmount = goal.savedAmount,
                goalAmount = goal.goalAmount,
                progressPercentage = goal.progressPercentage
            )

            SavingsDepositList(
                deposits = goal.deposits,
                modifier = Modifier.weight(1f)
            )

            CategoryAddSavingsButton(
                onClick = { /* TODO: Navigate to add deposit screen */ }
            )
        },
        navController = navController
    )
}
