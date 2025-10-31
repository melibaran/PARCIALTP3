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
                title = "Taxi",
                amount = 25.00,
                dateTime = "18:30 - April 29",
                iconId = R.drawable.transport,
                month = "April"
            ),
            TransportTransaction(
                id = "2",
                title = "Bus Ticket",
                amount = 12.50,
                dateTime = "08:15 - April 26",
                iconId = R.drawable.transport,
                month = "April"
            ),
            TransportTransaction(
                id = "3",
                title = "Uber",
                amount = 18.75,
                dateTime = "20:00 - April 20",
                iconId = R.drawable.transport,
                month = "April"
            ),
            TransportTransaction(
                id = "4",
                title = "Metro Card",
                amount = 30.00,
                dateTime = "07:00 - April 15",
                iconId = R.drawable.transport,
                month = "April"
            ),
            TransportTransaction(
                id = "5",
                title = "Taxi",
                amount = 22.00,
                dateTime = "19:30 - March 28",
                iconId = R.drawable.transport,
                month = "March"
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

