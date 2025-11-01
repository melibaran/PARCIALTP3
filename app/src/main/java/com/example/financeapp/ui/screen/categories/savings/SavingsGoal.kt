package com.example.financeapp.ui.screen.categories.savings

data class SavingsGoal(
    val title: String,
    val iconId: Int,
    val goalAmount: Double,
    val savedAmount: Double,
    val progressPercentage: Int,
    val deposits: Map<String, List<Deposit>>
)
