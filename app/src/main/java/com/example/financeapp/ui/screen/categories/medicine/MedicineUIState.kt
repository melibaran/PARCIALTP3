package com.example.financeapp.ui.screen.categories.medicine

data class MedicineUIState(
    val balance: Double = 7783.00,
    val totalExpense: Double = 850.50,
    val expensePercentage: Int = 25,
    val budget: Double = 20000.00,
    val transactions: List<MedicineTransaction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class MedicineTransaction(
    val id: String,
    val title: String,
    val amount: Double,
    val dateTime: String,
    val iconId: Int,
    val month: String
)

