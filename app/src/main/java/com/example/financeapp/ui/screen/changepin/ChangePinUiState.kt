package com.example.financeapp.ui.screen.changepin

data class ChangePinUiState(
    val currentPin: String = "",
    val newPin: String = "",
    val confirmPin: String = "",
    val currentPinVisible: Boolean = false,
    val newPinVisible: Boolean = false,
    val confirmPinVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

