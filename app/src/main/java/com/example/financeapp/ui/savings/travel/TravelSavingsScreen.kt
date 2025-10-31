package com.example.financeapp.ui.savings.travel

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.financeapp.R
import com.example.financeapp.ui.savings.Deposit
import com.example.financeapp.ui.savings.SavingsGoal
import com.example.financeapp.ui.savings.SavingsScreen

@Composable
fun TravelSavingsScreen() {
    val travelGoal = SavingsGoal(
        title = "Travel",
        painter = painterResource(id = R.drawable.travel),
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

    SavingsScreen(savingsGoal = travelGoal)
}