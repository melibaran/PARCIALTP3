package com.example.financeapp.ui.screen.categories.savings.newHouse

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.screen.categories.savings.Deposit
import com.example.financeapp.ui.screen.categories.savings.SavingsGoal
import com.example.financeapp.ui.screen.categories.savings.SavingsViewModel
import com.example.financeapp.ui.screen.savings.SavingsScreen

@Composable
fun HouseSavingsScreen(
    navController: NavController,
    viewModel: SavingsViewModel = hiltViewModel()
) {
    val houseGoal = SavingsGoal(
        title = "New House",
        painter = painterResource(id = R.drawable.newhome),
        goalAmount = 569200.0,
        savedAmount = 625.48,
        progressPercentage = 30,
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

    LaunchedEffect(Unit) {
        viewModel.addSavingsGoal(houseGoal)
    }

    SavingsScreen(navController = navController, viewModel = viewModel)
}