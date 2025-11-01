package com.example.financeapp.ui.screen.categories.entertainment

data class EntertainmentUIState(
    val balance: Double = 4850.00,
    val totalExpense: Double = 680.90,
    val expensePercentage: Int = 28,
    val budget: Double = 12000.00,
    val transactions: List<EntertainmentTransaction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class EntertainmentTransaction(
    val id: String,
    val title: String,
    val amount: Double,
    val dateTime: String,
    val iconId: Int,
    val month: String
)

