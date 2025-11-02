package com.example.financeapp.ui.screen.categories.rent

data class RentUIState(
    val balance: Double = 7783.00,
    val totalExpense: Double = 1500.00,
    val expensePercentage: Int = 40,
    val budget: Double = 20000.00,
    val transactions: List<RentTransaction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class RentTransaction(
    val id: String,
    val title: String,
    val amount: Double,
    val dateTime: String,
    val iconId: Int,
    val month: String
)

