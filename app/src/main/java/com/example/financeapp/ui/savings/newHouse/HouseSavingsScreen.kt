package com.example.financeapp.ui.savings.newHouse

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.financeapp.R
import com.example.financeapp.ui.savings.Deposit
import com.example.financeapp.ui.savings.SavingsGoal
import com.example.financeapp.ui.savings.SavingsScreen

@Composable
fun HouseSavingsScreen() {
    val houseGoal = SavingsGoal(
        title = "New House",
        painter = painterResource(id = R.drawable.new_home),
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

    SavingsScreen(savingsGoal = houseGoal)
}