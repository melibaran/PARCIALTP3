package com.example.financeapp.ui.savings.car

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.financeapp.R
import com.example.financeapp.ui.savings.Deposit
import com.example.financeapp.ui.savings.SavingsGoal
import com.example.financeapp.ui.savings.SavingsScreen

@Composable
fun CarSavingsScreen() {
    val carGoal = SavingsGoal(
        title = "Car",
        painter = painterResource(id = R.drawable.car_me),
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

    SavingsScreen(savingsGoal = carGoal)
}