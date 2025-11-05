package com.example.financeapp.ui.screen.categories.gift

import androidx.lifecycle.ViewModel
import com.example.financeapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

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
                title = "Perfume",
                amount = 30.00,
                dateTime = "10:27 - April 28",
                iconId = R.drawable.gift_vector,
                month = "April"
            ),
            GiftTransaction(
                id = "2",
                title = "Make-Up",
                amount = 60.35,
                dateTime = "16:28 - April 15",
                iconId = R.drawable.gift_vector,
                month = "April"
            ),
            GiftTransaction(
                id = "3",
                title = "Teddy Bear",
                amount = 20.00,
                dateTime = "8.30 - March 10",
                iconId = R.drawable.gift_vector,
                month = "March"
            ),
            GiftTransaction(
                id = "4",
                title = "Cooking Lessons",
                amount = 128.00,
                dateTime = "14:24 - March 02",
                iconId = R.drawable.gift_vector,
                month = "March"
            ),
            GiftTransaction(
                id = "5",
                title = "Toys For Dani",
                amount = 50.20,
                dateTime = "11:15 - February 18",
                iconId = R.drawable.gift_vector,
                month = "February"
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

