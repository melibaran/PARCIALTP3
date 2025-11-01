package com.example.financeapp.ui.screen.categories.entertainment

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.financeapp.R

@HiltViewModel
class EntertainmentViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(EntertainmentUIState())
    val uiState: StateFlow<EntertainmentUIState> = _uiState.asStateFlow()

    init {
        loadEntertainmentTransactions()
    }

    private fun loadEntertainmentTransactions() {
        val mockTransactions = listOf(
            EntertainmentTransaction(
                id = "1",
                title = "Cinema",
                amount = 35.00,
                dateTime = "20:00 - April 27",
                iconId = R.drawable.entertainment,
                month = "April"
            ),
            EntertainmentTransaction(
                id = "2",
                title = "Concert",
                amount = 150.00,
                dateTime = "19:30 - April 22",
                iconId = R.drawable.entertainment,
                month = "April"
            ),
            EntertainmentTransaction(
                id = "3",
                title = "Netflix",
                amount = 15.99,
                dateTime = "10:00 - April 15",
                iconId = R.drawable.entertainment,
                month = "April"
            ),
            EntertainmentTransaction(
                id = "4",
                title = "Gaming",
                amount = 59.99,
                dateTime = "14:30 - April 08",
                iconId = R.drawable.entertainment,
                month = "April"
            ),
            EntertainmentTransaction(
                id = "5",
                title = "Theater",
                amount = 80.00,
                dateTime = "18:00 - March 30",
                iconId = R.drawable.entertainment,
                month = "March"
            )
        )

        _uiState.value = _uiState.value.copy(
            transactions = mockTransactions
        )
    }

    fun getTransactionsByMonth(month: String): List<EntertainmentTransaction> {
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

