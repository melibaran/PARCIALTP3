package com.example.financeapp.ui.screen.categories.food

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.financeapp.R

@HiltViewModel
class FoodViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(FoodUIState())
    val uiState: StateFlow<FoodUIState> = _uiState.asStateFlow()

    init {
        loadFoodTransactions()
    }

    private fun loadFoodTransactions() {
        val mockTransactions = listOf(
            FoodTransaction(
                id = "1",
                title = "Dinner",
                amount = 26.00,
                dateTime = "18:27 - April 30",
                iconId = R.drawable.food_vector,
                month = "April"
            ),
            FoodTransaction(
                id = "2",
                title = "Delivery Pizza",
                amount = 18.35,
                dateTime = "15:00 - April 24",
                iconId = R.drawable.food_vector,
                month = "April"
            ),
            FoodTransaction(
                id = "3",
                title = "Lunch",
                amount = 15.40,
                dateTime = "12:30 - April 15",
                iconId = R.drawable.food_vector,
                month = "April"
            ),
            FoodTransaction(
                id = "4",
                title = "Brunch",
                amount = 12.13,
                dateTime = "9:30 - April 08",
                iconId = R.drawable.food_vector,
                month = "April"
            ),
            FoodTransaction(
                id = "5",
                title = "Dinner",
                amount = 27.20,
                dateTime = "20:50 - March 31",
                iconId = R.drawable.food_vector,
                month = "March"
            )
        )

        _uiState.value = _uiState.value.copy(
            transactions = mockTransactions
        )
    }

    fun getTransactionsByMonth(month: String): List<FoodTransaction> {
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


