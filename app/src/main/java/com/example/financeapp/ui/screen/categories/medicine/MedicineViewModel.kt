package com.example.financeapp.ui.screen.categories.medicine

import androidx.lifecycle.ViewModel
import com.example.financeapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MedicineUIState())
    val uiState: StateFlow<MedicineUIState> = _uiState.asStateFlow()

    init {
        loadMedicineTransactions()
    }

    private fun loadMedicineTransactions() {
        val mockTransactions = listOf(
            MedicineTransaction(
                id = "1",
                title = "Acetominophen",
                amount = 2.00,
                dateTime = "12:11 - April 30",
                iconId = R.drawable.medicine_vector,
                month = "April"
            ),
            MedicineTransaction(
                id = "2",
                title = "Vitamin C",
                amount = 8.20,
                dateTime = "12:30 - March 20",
                iconId = R.drawable.medicine_vector,
                month = "March"
            ),
            MedicineTransaction(
                id = "3",
                title = "Muscle Pain Cream",
                amount = 10.13,
                dateTime = "9:30 - March 12",
                iconId = R.drawable.medicine_vector,
                month = "March"
            ),
            MedicineTransaction(
                id = "4",
                title = "Aspirin",
                amount = 2.20,
                dateTime = "18:30 - February 02",
                iconId = R.drawable.medicine_vector,
                month = "February"
            )
        )

        val totalExpense = mockTransactions.sumOf { it.amount }
        _uiState.value = _uiState.value.copy(
            transactions = mockTransactions,
            totalExpense = totalExpense
        )
    }

    fun getTransactionsByMonth(month: String): List<MedicineTransaction> {
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

