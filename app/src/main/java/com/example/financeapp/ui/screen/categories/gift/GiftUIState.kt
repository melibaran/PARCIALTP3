package com.example.financeapp.ui.screen.categories.gift

data class GiftUIState(
    val balance: Double = 7783.00,
    val totalExpense: Double = 450.75,
    val expensePercentage: Int = 15,
    val budget: Double = 20000.00,
    val transactions: List<GiftTransaction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class GiftTransaction(
    val id: String,
    val title: String,
    val amount: Double,
    val dateTime: String,
    val iconId: Int,
    val month: String
)

