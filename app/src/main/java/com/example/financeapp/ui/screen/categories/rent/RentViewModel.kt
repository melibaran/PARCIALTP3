package com.example.financeapp.ui.screen.categories.rent

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.financeapp.R

@HiltViewModel
class RentViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(RentUIState())
    val uiState: StateFlow<RentUIState> = _uiState.asStateFlow()

    init {
        loadRentTransactions()
    }

    private fun loadRentTransactions() {
        val mockTransactions = listOf(
            RentTransaction(
                id = "1",
                title = "Rent",
                amount = 674.40,
                dateTime = "18:27 - April 15",
                iconId = R.drawable.rent_vector,
                month = "April"
            ),
            RentTransaction(
                id = "2",
                title = "Rent",
                amount = 674.40,
                dateTime = "15:00 - March 15",
                iconId = R.drawable.rent_vector,
                month = "March"
            ),
            RentTransaction(
                id = "3",
                title = "Rent",
                amount = 674.40,
                dateTime = "11:47 - February 15",
                iconId = R.drawable.rent_vector,
                month = "February"
            ),
            RentTransaction(
                id = "4",
                title = "Rent",
                amount = 674.40,
                dateTime = "18:50 - January 15",
                iconId = R.drawable.rent_vector,
                month = "January"
            )
        )

        val totalExpense = mockTransactions.sumOf { it.amount }
        _uiState.value = _uiState.value.copy(
            transactions = mockTransactions,
            totalExpense = totalExpense
        )
    }

    fun getTransactionsByMonth(month: String): List<RentTransaction> {
        return _uiState.value.transactions.filter { it.month == month }
    }

    fun getAvailableMonths(): List<String> {
        return _uiState.value.transactions
            .map { it.month }
            .distinct()
            .sortedByDescending { month ->
                when (month) {
                    "April" -> 4
                    "March" -> 3
                    "February" -> 2
                    "January" -> 1
                    else -> 0
                }
            }
    }
}

