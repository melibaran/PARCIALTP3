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
                amount = 30.00,
                dateTime = "20:15 - April 29",
                iconId = R.drawable.entertaiment_vector,
                month = "April"
            ),
            EntertainmentTransaction(
                id = "2",
                title = "Netflix",
                amount = 12.27,
                dateTime = "16:15 - April 12",
                iconId = R.drawable.entertaiment_vector,
                month = "April"
            ),
            EntertainmentTransaction(
                id = "3",
                title = "Karaoke",
                amount = 110.00,
                dateTime = "18:00 - April 05",
                iconId = R.drawable.entertaiment_vector,
                month = "April"
            ),
            EntertainmentTransaction(
                id = "4",
                title = "Video Game",
                amount = 60.20,
                dateTime = "20:50 - March 24",
                iconId = R.drawable.entertaiment_vector,
                month = "March"
            ),
            EntertainmentTransaction(
                id = "5",
                title = "Netflix",
                amount = 12.27,
                dateTime = "16:15 - March 12",
                iconId = R.drawable.entertaiment_vector,
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

