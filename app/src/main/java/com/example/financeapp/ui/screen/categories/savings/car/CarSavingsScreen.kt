package com.example.financeapp.ui.screen.categories.savings.car

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
fun CarSavingsScreen(
    navController: NavController,
    viewModel: SavingsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val existingGoal = uiState.savingsGoals.find { it.title == "Car" }

    LaunchedEffect(existingGoal) {
        existingGoal?.let { goal ->
            val carGoalWithDeposits = goal.copy(
                deposits = mapOf(
                    "July" to listOf(
                        Deposit("Car Deposit", "14:16", "July 5", 387.32)
                    ),
                    "May" to listOf(
                        Deposit("Car Deposit", "21:45", "May 30", 122.99),
                        Deposit("Car Deposit", "14:25", "May 05", 85.94)
                    )
                )
            )
            viewModel.updateSavingsGoal(carGoalWithDeposits)
        }
    }

    val goal = existingGoal ?: return

    CategoryDesign(
        title = "Car",
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
