package com.example.financeapp.presentation.screen.transaction

import androidx.lifecycle.ViewModel
import com.example.financeapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionUIState())
    val uiState: StateFlow<TransactionUIState> = _uiState.asStateFlow()

    init {
        loadMockData()
    }

    private fun loadMockData() {
        val mockTransactions = listOf(
            TransactionItem(
                id = "1",
                title = "Salary",
                amount = 4000.00,
                category = "Monthly",
                dateTime = "18:27 - April 30",
                iconId = R.drawable.salary,
                isIncome = true,
                month = "April"
            ),
            TransactionItem(
                id = "2",
                title = "Groceries",
                amount = -100.00,
                category = "Pantry",
                dateTime = "17:00 - April 24",
                iconId = R.drawable.groceries,
                isIncome = false,
                month = "April"
            ),
            TransactionItem(
                id = "3",
                title = "Rent",
                amount = -674.40,
                category = "Rent",
                dateTime = "8:30 - April 15",
                iconId = R.drawable.rent,
                isIncome = false,
                month = "April"
            ),
            TransactionItem(
                id = "4",
                title = "Transport",
                amount = -4.13,
                category = "Fuel",
                dateTime = "9:30 - April 08",
                iconId = R.drawable.transport,
                isIncome = false,
                month = "April"
            ),
            TransactionItem(
                id = "5",
                title = "Food",
                amount = -70.40,
                category = "Dinner",
                dateTime = "19:30 - March 31",
                iconId = R.drawable.food,
                isIncome = false,
                month = "March"
            )
        )

        _uiState.value = _uiState.value.copy(
            transactions = mockTransactions
        )
    }

    fun getTransactionsByMonth(month: String): List<TransactionItem> {
        return uiState.value.transactions.filter { it.month == month }
    }
}
