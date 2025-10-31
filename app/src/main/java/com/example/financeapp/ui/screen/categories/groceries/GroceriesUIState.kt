package com.example.financeapp.ui.screen.categories.groceries

data class GroceriesUIState(
    val balance: Double = 6200.00,
    val totalExpense: Double = 1250.30,
    val expensePercentage: Int = 35,
    val budget: Double = 18000.00,
    val transactions: List<GroceriesTransaction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class GroceriesTransaction(
    val id: String,
    val title: String,
    val amount: Double,
    val dateTime: String,
    val iconId: Int,
    val month: String
)

