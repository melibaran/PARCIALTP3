package com.example.financeapp.ui.screen.profile.changepin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.R
import com.example.financeapp.core.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PIN_MIN_LEN = 4

@HiltViewModel
class ChangePinViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChangePinUiState())
    val uiState: StateFlow<ChangePinUiState> = _uiState.asStateFlow()

    fun onCurrentPinChange(pin: String) {
        if (pin.length <= PIN_MIN_LEN && pin.all { it.isDigit() }) {
            _uiState.update { it.copy(currentPin = pin, errorMessage = null) }
        }
    }

    fun onNewPinChange(pin: String) {
        if (pin.length <= PIN_MIN_LEN && pin.all { it.isDigit() }) {
            _uiState.update { it.copy(newPin = pin, errorMessage = null) }
        }
    }

    fun onConfirmPinChange(pin: String) {
        if (pin.length <= PIN_MIN_LEN && pin.all { it.isDigit() }) {
            _uiState.update { it.copy(confirmPin = pin, errorMessage = null) }
        }
    }

    fun toggleCurrentPinVisibility() {
        _uiState.update { it.copy(currentPinVisible = !it.currentPinVisible) }
    }

    fun toggleNewPinVisibility() {
        _uiState.update { it.copy(newPinVisible = !it.newPinVisible) }
    }

    fun toggleConfirmPinVisibility() {
        _uiState.update { it.copy(confirmPinVisible = !it.confirmPinVisible) }
    }

    fun changePin() {
        val state = _uiState.value

        if (state.currentPin.length != PIN_MIN_LEN) {
            _uiState.update { 
                it.copy(errorMessage = resourceProvider.getString(R.string.error_current_pin_length)) 
            }
            return
        }

        if (state.newPin.length != PIN_MIN_LEN) {
            _uiState.update { 
                it.copy(errorMessage = resourceProvider.getString(R.string.error_new_pin_length)) 
            }
            return
        }

        if (state.newPin != state.confirmPin) {
            _uiState.update { 
                it.copy(errorMessage = resourceProvider.getString(R.string.error_pins_dont_match)) 
            }
            return
        }

        if (state.currentPin == state.newPin) {
            _uiState.update { 
                it.copy(errorMessage = resourceProvider.getString(R.string.error_pin_same_as_current)) 
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                delay(1500)

                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: resourceProvider.getString(R.string.error_change_pin_failed)
                    )
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun resetSuccessState() {
        _uiState.update { it.copy(isSuccess = false) }
    }
}

