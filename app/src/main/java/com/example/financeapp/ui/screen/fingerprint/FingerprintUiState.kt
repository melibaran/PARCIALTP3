package com.example.financeapp.ui.screen.fingerprint

data class FingerprintUiState(
    val fingerprints: List<FingerprintItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class FingerprintItem(
    val id: String,
    val name: String
)

