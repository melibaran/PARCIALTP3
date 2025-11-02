package com.example.financeapp.ui.screen.categories.transport

data class TransportUIState(
    val balance: Double = 5420.00,
    val totalExpense: Double = 890.50,
    val expensePercentage: Int = 25,
    val budget: Double = 15000.00,
    val transactions: List<TransportTransaction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class TransportTransaction(
    val id: String,
    val title: String,
    val amount: Double,
    val dateTime: String,
    val iconId: Int,
    val month: String
)

