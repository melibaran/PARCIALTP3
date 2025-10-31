package com.example.financeapp.ui.screen.transaction

data class TransactionUIState(
    val balance: Double = 0.0,
    val totalExpense: Double = 0.0,
    val expenseGoal: Double = 0.0,
    val expensePercentage: Int = 0,
    val transactions: List<TransactionItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class TransactionItem(
    val id: String,
    val title: String,
    val amount: Double,
    val category: String,
    val dateTime: String,
    val iconId: Int,
    val isIncome: Boolean,
    val month: String
)
