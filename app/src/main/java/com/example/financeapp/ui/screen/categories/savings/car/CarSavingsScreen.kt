package com.example.financeapp.ui.screen.categories.savings.car

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.screen.categories.savings.Deposit
import com.example.financeapp.ui.screen.categories.savings.SavingsGoal
import com.example.financeapp.ui.screen.categories.savings.SavingsViewModel
import com.example.financeapp.ui.screen.savings.SavingsScreen

@Composable
fun CarSavingsScreen(
    navController: NavController,
    viewModel: SavingsViewModel = hiltViewModel()
) {
    val carGoal = SavingsGoal(
        title = "Car",
        iconId = R.drawable.car,
        goalAmount = 14390.0,
        savedAmount = 596.25,
        progressPercentage = 15,
        deposits = mapOf(
            "July" to listOf(
                Deposit("Car Deposit", "14:16", "July 5", 387.32)
            ),
            "May" to listOf(
                Deposit("Car Deposit", "21:45", "May 30", 122.99),
                Deposit("House Deposit", "14:25", "May 05", 85.94)
            )
        )
    )

    LaunchedEffect(Unit) {
        viewModel.addSavingsGoal(carGoal)
    }

    SavingsScreen(navController = navController, viewModel = viewModel)
}