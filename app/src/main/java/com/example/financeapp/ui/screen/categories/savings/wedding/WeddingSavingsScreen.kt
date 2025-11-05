package com.example.financeapp.ui.screen.categories.savings.wedding

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

    val goal = existingGoal ?: return

    CategoryDesign(
        title = "Wedding",
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
                onClick = { navController.navigate("add_savings") },
                title = "Add Savings"
            )
        },
        navController = navController
    )
}