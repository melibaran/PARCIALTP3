package com.example.financeapp.ui.screen.categories.savings.wedding

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
fun WeddingSavingsScreen(
    navController: NavController,
    viewModel: SavingsViewModel = hiltViewModel()
) {
    val weddingGoal = SavingsGoal(
        title = "Wedding",
        painter = painterResource(id = R.drawable.wedding_me),
        goalAmount = 34700.0,
        savedAmount = 296.25,
        progressPercentage = 10,
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

    LaunchedEffect(Unit) {
        viewModel.addSavingsGoal(weddingGoal)
    }

    SavingsScreen(navController = navController, viewModel = viewModel)
}