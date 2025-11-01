package com.example.financeapp.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.core.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PASSWORD_MIN_LEN = 6

@HiltViewModel
class PasswordSettingsViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(PasswordSettingsUiState())
    val uiState: StateFlow<PasswordSettingsUiState> = _uiState.asStateFlow()

    fun onCurrentPasswordChange(pw: String) {
        if (pw.length <= 32) {
            _uiState.update { it.copy(currentPassword = pw, errorMessage = null) }
        }
    }

    fun onNewPasswordChange(pw: String) {
        if (pw.length <= 32) {
            _uiState.update { it.copy(newPassword = pw, errorMessage = null) }
        }
    }

    fun onConfirmPasswordChange(pw: String) {
        if (pw.length <= 32) {
            _uiState.update { it.copy(confirmPassword = pw, errorMessage = null) }
        }
    }

    fun toggleCurrentPasswordVisibility() {
        _uiState.update { it.copy(currentPasswordVisible = !it.currentPasswordVisible) }
    }

    fun toggleNewPasswordVisibility() {
        _uiState.update { it.copy(newPasswordVisible = !it.newPasswordVisible) }
    }

    fun toggleConfirmPasswordVisibility() {
        _uiState.update { it.copy(confirmPasswordVisible = !it.confirmPasswordVisible) }
    }

    fun changePassword() {
        val state = _uiState.value

        if (state.currentPassword.length < PASSWORD_MIN_LEN) {
            _uiState.update { it.copy(errorMessage = "Current password must be at least $PASSWORD_MIN_LEN characters") }
            return
        }

        if (state.newPassword.length < PASSWORD_MIN_LEN) {
            _uiState.update { it.copy(errorMessage = "New password must be at least $PASSWORD_MIN_LEN characters") }
            return
        }

        if (state.newPassword != state.confirmPassword) {
            _uiState.update { it.copy(errorMessage = "Passwords don't match") }
            return
        }

        if (state.currentPassword == state.newPassword) {
            _uiState.update { it.copy(errorMessage = "New password must be different from current password") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                delay(1200)
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Failed to change password") }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun resetSuccess() {
        _uiState.update { it.copy(isSuccess = false) }
    }
}
