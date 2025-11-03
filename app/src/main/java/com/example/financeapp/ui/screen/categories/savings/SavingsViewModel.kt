package com.example.financeapp.ui.screen.categories.savings

import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


data class SavingsUiState(
    val balance: Double = 7783.00,
    val totalExpense: Double = 1187.40,
    val budget: Double = 20000.00,
    val expensePercentage: Int = 30,
    val savingsGoals: List<SavingsGoal> = emptyList()
)

@HiltViewModel
class SavingsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(
        SavingsUiState(
            savingsGoals = getDefaultSavingsGoals()
        )
    )
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

    fun updateSavingsGoal(goal: SavingsGoal) {
        _uiState.value = _uiState.value.copy(
            savingsGoals = _uiState.value.savingsGoals.map {
                if (it.title == goal.title) goal else it
            }
        )
    }

    private fun getDefaultSavingsGoals(): List<SavingsGoal> {
        return emptyList() // Se inicializan en runtime con los painters
    }
}