package com.example.financeapp.ui.screen.categories.groceries

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.financeapp.R

@HiltViewModel
class GroceriesViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(GroceriesUIState())
    val uiState: StateFlow<GroceriesUIState> = _uiState.asStateFlow()

    init {
        loadGroceriesTransactions()
    }

    private fun loadGroceriesTransactions() {
        val mockTransactions = listOf(
            GroceriesTransaction(
                id = "1",
                title = "Supermarket",
                amount = 120.50,
                dateTime = "10:30 - April 28",
                iconId = R.drawable.groceries,
                month = "April"
            ),
            GroceriesTransaction(
                id = "2",
                title = "Fresh Market",
                amount = 85.75,
                dateTime = "16:00 - April 25",
                iconId = R.drawable.groceries,
                month = "April"
            ),
            GroceriesTransaction(
                id = "3",
                title = "Walmart",
                amount = 95.20,
                dateTime = "11:45 - April 18",
                iconId = R.drawable.groceries,
                month = "April"
            ),
            GroceriesTransaction(
                id = "4",
                title = "Costco",
                amount = 210.00,
                dateTime = "14:20 - April 10",
                iconId = R.drawable.groceries,
                month = "April"
            ),
            GroceriesTransaction(
                id = "5",
                title = "Local Store",
                amount = 45.30,
                dateTime = "09:15 - March 29",
                iconId = R.drawable.groceries,
                month = "March"
            )
        )

        _uiState.value = _uiState.value.copy(
            transactions = mockTransactions
        )
    }

    fun getTransactionsByMonth(month: String): List<GroceriesTransaction> {
        return _uiState.value.transactions.filter { it.month == month }
    }

    fun getAvailableMonths(): List<String> {
        return _uiState.value.transactions
            .map { it.month }
            .distinct()
            .sortedByDescending {
                when(it) {
                    "April" -> 4
                    "March" -> 3
                    "February" -> 2
                    "January" -> 1
                    else -> 0
                }
            }
    }
}

