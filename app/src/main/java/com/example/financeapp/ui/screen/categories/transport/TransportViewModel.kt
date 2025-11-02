package com.example.financeapp.ui.screen.categories.transport

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.financeapp.R

@HiltViewModel
class TransportViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(TransportUIState())
    val uiState: StateFlow<TransportUIState> = _uiState.asStateFlow()

    init {
        loadTransportTransactions()
    }

    private fun loadTransportTransactions() {
        val mockTransactions = listOf(
            TransportTransaction(
                id = "1",
                title = "Fuel",
                amount = 3.53,
                dateTime = "18:27 - March 20",
                iconId = R.drawable.transport_vector,
                month = "March"
            ),
            TransportTransaction(
                id = "2",
                title = "Car Parts",
                amount = 26.75,
                dateTime = "15:00 - March 30",
                iconId = R.drawable.transport_vector,
                month = "March"
            ),

            TransportTransaction(
            id = "3",
            title = "New Tires",
            amount = 373.99,
            dateTime = "12:47 - February 10",
            iconId = R.drawable.transport_vector,
            month = "February"
        ),
            TransportTransaction(
            id = "4",
            title = "Car Wash",
            amount = 9.74,
            dateTime = "9:30 - February 10",
            iconId = R.drawable.transport_vector,
            month = "February"
            ),
            TransportTransaction(
                id = "5",
                title = "Public transport",
                amount = 1.24,
                dateTime = "9:30 - February 10",
                iconId = R.drawable.transport_vector,
                month = "February"
            )
        )

        _uiState.value = _uiState.value.copy(
            transactions = mockTransactions
        )
    }

    fun getTransactionsByMonth(month: String): List<TransportTransaction> {
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

