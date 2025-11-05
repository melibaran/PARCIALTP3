package com.example.financeapp.ui.screen.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.infrastructure.api.ApiClient
import com.example.financeapp.ui.mapper.TransactionMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val apiClient: ApiClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionUIState())
    val uiState: StateFlow<TransactionUIState> = _uiState.asStateFlow()
    
    private var _selectedMonth: MutableStateFlow<String?> = MutableStateFlow(null)
    val selectedMonth: StateFlow<String?> = _selectedMonth.asStateFlow()

    init {
        loadTransactions()
    }


    fun loadTransactions() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                
                val userTransactions = apiClient.getTransactions()
                
                val transactionItems = TransactionMapper.toTransactionItems(userTransactions.transactions)
                
                val totalExpense = userTransactions.expense
                val expenseGoal = userTransactions.income
                val expensePercentage = if (expenseGoal > 0) {
                    ((totalExpense / expenseGoal) * 100).toInt()
                } else {
                    0
                }

                _uiState.value = _uiState.value.copy(
                    balance = userTransactions.balance,
                    totalExpense = totalExpense,
                    expenseGoal = expenseGoal,
                    expensePercentage = expensePercentage,
                    transactions = transactionItems,
                    isLoading = false
                )
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Error al cargar transacciones"
                )
            }
        }
    }

    fun getTransactionsByMonth(month: String): List<TransactionItem> {
        return uiState.value.transactions.filter { it.month == month }
    }
    
    fun getAvailableMonths(): List<String> {
        return uiState.value.transactions
            .map { it.month }
            .distinct()
            .sortedByDescending { monthName ->
                // Ordenar por fecha (los más recientes primero)
                val monthIndex = listOf(
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
                ).indexOf(monthName)
                monthIndex
            }
    }

    fun selectMonth(month: String?) {
        _selectedMonth.value = month
    }
}
