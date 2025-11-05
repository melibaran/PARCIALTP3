package com.example.financeapp.ui.screen.categories

data class CategoriesUIState(
    val balance: Double = 7783.00,
    val totalExpense: Double = 1187.40,
    val budget: Double = 20000.00,
    val expensePercentage: Int = 30,
    val categories: List<CategoryItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showDialog: Boolean = false
)

data class CategoryItem(
    val id: String,
    val name: String,
    val iconId: Int
)

