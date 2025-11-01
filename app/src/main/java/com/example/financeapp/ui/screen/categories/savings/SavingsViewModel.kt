package com.example.financeapp.ui.screen.categories.savings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


data class SavingsUiState(
    val balance: String = "7,783.00",
    val totalExpense: String = "1,187.40",
    val budget: String = "20,000.00",
    val expensePercentage: Int = 30,
    val savingsGoals: List<SavingsGoal> = emptyList()
)

@HiltViewModel
class SavingsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(SavingsUiState())
    val uiState: StateFlow<SavingsUiState> = _uiState.asStateFlow()

    fun addSavingsGoal(goal: SavingsGoal) {
        _uiState.value = _uiState.value.copy(
            savingsGoals = _uiState.value.savingsGoals + goal
        )
    }

    fun removeSavingsGoal(goalTitle: String) {
        _uiState.value = _uiState.value.copy(
            savingsGoals = _uiState.value.savingsGoals.filter { it.title != goalTitle }
        )
    }
}