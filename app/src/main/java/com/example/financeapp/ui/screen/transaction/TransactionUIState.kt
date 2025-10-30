package com.example.financeapp.ui.screen.transaction

data class TransactionUIState(
    val balance: Double = 7783.00,
    val totalExpense: Double = 1187.40,
    val expenseGoal: Double = 20000.00,
    val expensePercentage: Int = 30,
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
