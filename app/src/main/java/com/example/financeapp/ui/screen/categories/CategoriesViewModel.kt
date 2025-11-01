package com.example.financeapp.ui.screen.categories

import androidx.lifecycle.ViewModel
import com.example.financeapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CategoriesUIState())
    val uiState: StateFlow<CategoriesUIState> = _uiState.asStateFlow()

    init {
        loadMockData()
    }

    private fun loadMockData() {
        val mockCategories = listOf(
            CategoryItem(
                id = "1",
                name = "Food",
                iconId = R.drawable.food
            ),
            CategoryItem(
                id = "2",
                name = "Transport",
                iconId = R.drawable.transport
            ),
            CategoryItem(
                id = "3",
                name = "Medicine",
                iconId = R.drawable.medicine
            ),
            CategoryItem(
                id = "4",
                name = "Groceries",
                iconId = R.drawable.groceries
            ),
            CategoryItem(
                id = "5",
                name = "Rent",
                iconId = R.drawable.rent
            ),
            CategoryItem(
                id = "6",
                name = "Gift",
                iconId = R.drawable.gift
            ),
            CategoryItem(
                id = "7",
                name = "Savings",
                iconId = R.drawable.saving
            ),
            CategoryItem(
                id = "8",
                name = "Entertainment",
                iconId = R.drawable.entertainment
            ),
            CategoryItem(
                id = "9",
                name = "More",
                iconId = R.drawable.more
            )
        )

        _uiState.value = _uiState.value.copy(
            categories = mockCategories
        )
    }
}

