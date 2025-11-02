package com.example.financeapp.ui.screen.categories.savings.travel

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
fun TravelSavingsScreen(
    navController: NavController,
    viewModel: SavingsViewModel = hiltViewModel()
) {
    val travelGoal = SavingsGoal(
        title = "Travel",
        iconId = R.drawable.travel,
        goalAmount = 1962.93,
        savedAmount = 653.31,
        progressPercentage = 40,
        deposits = mapOf(
            "April" to listOf(
                Deposit("Travel Deposit", "19:56", "April 30", 217.77),
                Deposit("Travel Deposit", "17:42", "April 14", 217.77),
                Deposit("Travel Deposit", "12:30", "April 02", 217.77)
            )
        )
    )

    LaunchedEffect(Unit) {
        viewModel.addSavingsGoal(travelGoal)
    }

    SavingsScreen(navController = navController, viewModel = viewModel)
}