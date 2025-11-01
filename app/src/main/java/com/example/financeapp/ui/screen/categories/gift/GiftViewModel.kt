package com.example.financeapp.ui.screen.categories.gift

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.financeapp.R

@HiltViewModel
class GiftViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(GiftUIState())
    val uiState: StateFlow<GiftUIState> = _uiState.asStateFlow()

    init {
        loadGiftTransactions()
    }

    private fun loadGiftTransactions() {
        val mockTransactions = listOf(
            GiftTransaction(
                id = "1",
                title = "Birthday Gift",
                amount = 75.00,
                dateTime = "11:30 - April 26",
                iconId = R.drawable.gift,
                month = "April"
            ),
            GiftTransaction(
                id = "2",
                title = "Anniversary",
                amount = 150.00,
                dateTime = "14:00 - April 20",
                iconId = R.drawable.gift,
                month = "April"
            ),
            GiftTransaction(
                id = "3",
                title = "Wedding Gift",
                amount = 200.00,
                dateTime = "16:00 - April 12",
                iconId = R.drawable.gift,
                month = "April"
            ),
            GiftTransaction(
                id = "4",
                title = "Baby Shower",
                amount = 95.75,
                dateTime = "10:30 - March 28",
                iconId = R.drawable.gift,
                month = "March"
            )
        )

        val totalExpense = mockTransactions.sumOf { it.amount }
        _uiState.value = _uiState.value.copy(
            transactions = mockTransactions,
            totalExpense = totalExpense
        )
    }

    fun getTransactionsByMonth(month: String): List<GiftTransaction> {
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

