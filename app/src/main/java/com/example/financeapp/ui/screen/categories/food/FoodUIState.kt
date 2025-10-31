package com.example.financeapp.ui.screen.categories.food

data class FoodUIState(
    val balance: Double = 7783.00,
    val totalExpense: Double = 1137.40,
    val expensePercentage: Int = 30,
    val budget: Double = 20000.00,
    val transactions: List<FoodTransaction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class FoodTransaction(
    val id: String,
    val title: String,
    val amount: Double,
    val dateTime: String,
    val iconId: Int,
    val month: String
)

