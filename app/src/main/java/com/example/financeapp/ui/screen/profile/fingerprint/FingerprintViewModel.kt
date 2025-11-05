package com.example.financeapp.ui.screen.profile.fingerprint

import androidx.lifecycle.ViewModel
import com.example.financeapp.core.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FingerprintViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        FingerprintUiState(
            fingerprints = listOf(
                FingerprintItem(id = "1", name = "John Fingerprint")
            )
        )
    )
    val uiState: StateFlow<FingerprintUiState> = _uiState.asStateFlow()

    fun addFingerprint() {

    }

    fun removeFingerprint(id: String) {
        _uiState.update { 
            it.copy(
                fingerprints = it.fingerprints.filter { fp -> fp.id != id }
            )
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}

