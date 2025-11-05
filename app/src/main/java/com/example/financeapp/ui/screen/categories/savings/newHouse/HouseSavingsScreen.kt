package com.example.financeapp.ui.screen.categories.savings.newHouse

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

    val goal = existingGoal ?: return

    CategoryDesign(
        title = "New House",
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
