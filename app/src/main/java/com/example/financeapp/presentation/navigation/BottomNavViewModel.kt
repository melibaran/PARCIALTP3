package com.example.financeapp.presentation.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BottomNavViewModel : ViewModel() {
    private val _selectedRoute = MutableStateFlow("home")
    val selectedRoute: StateFlow<String> = _selectedRoute

    fun onNavItemSelected(route: String) {
        _selectedRoute.value = route
    }
}
