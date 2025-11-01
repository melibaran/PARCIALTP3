package com.example.financeapp.ui.screen.categories.medicine

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.financeapp.R

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
                title = "Pharmacy",
                amount = 45.00,
                dateTime = "14:20 - April 28",
                iconId = R.drawable.medicine,
                month = "April"
            ),
            MedicineTransaction(
                id = "2",
                title = "Doctor Visit",
                amount = 120.00,
                dateTime = "10:00 - April 22",
                iconId = R.drawable.medicine,
                month = "April"
            ),
            MedicineTransaction(
                id = "3",
                title = "Medication",
                amount = 65.50,
                dateTime = "16:45 - April 18",
                iconId = R.drawable.medicine,
                month = "April"
            ),
            MedicineTransaction(
                id = "4",
                title = "Lab Tests",
                amount = 85.00,
                dateTime = "09:00 - April 10",
                iconId = R.drawable.medicine,
                month = "April"
            ),
            MedicineTransaction(
                id = "5",
                title = "Pharmacy",
                amount = 55.00,
                dateTime = "18:30 - March 29",
                iconId = R.drawable.medicine,
                month = "March"
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

