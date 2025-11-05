package com.example.financeapp.ui.screen.categories.groceries

import androidx.lifecycle.ViewModel
import com.example.financeapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

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
                title = "Pantry",
                amount = 53.59,
                dateTime = "18:27 - March 10",
                iconId = R.drawable.groceries_vector,
                month = "March"
            ),
            GroceriesTransaction(
                id = "2",
                title = "Snacks",
                amount = 35.03,
                dateTime = "15:00 - March 10",
                iconId = R.drawable.groceries_vector,
                month = "March"
            ),
            GroceriesTransaction(
                id = "3",
                title = "Canned Food",
                amount = 11.82,
                dateTime = "10:47 - February 30",
                iconId = R.drawable.groceries_vector,
                month = "February"
            ),
            GroceriesTransaction(
                id = "4",
                title = "Veggies",
                amount = 14.79,
                dateTime = "7:30 - February 20",
                iconId = R.drawable.groceries_vector,
                month = "February"
            ),
            GroceriesTransaction(
                id = "5",
                title = "Groceries",
                amount = 175.35,
                dateTime = "18:50 - February 1",
                iconId = R.drawable.groceries_vector,
                month = "February"
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

