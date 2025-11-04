package com.example.financeapp.ui.screen.settings

import com.example.financeapp.R
import com.example.financeapp.core.ResourceProvider
import com.example.financeapp.ui.base.UiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val PASSWORD_MIN_LEN = 6

data class PasswordSettingsUiState(
    val currentPassword: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "",
    val currentPasswordVisible: Boolean = false,
    val newPasswordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

sealed class PasswordChangeState {
    data object Idle : PasswordChangeState()
    data object Loading : PasswordChangeState()
    data object Success : PasswordChangeState()
    data class Error(val message: String) : PasswordChangeState()
}

@HiltViewModel
class PasswordSettingsViewModel @Inject constructor(
    resourceProvider: ResourceProvider
) : UiStateViewModel<PasswordChangeState>(resourceProvider) {

    override val _state = MutableStateFlow<PasswordChangeState>(PasswordChangeState.Idle)
    override val state: StateFlow<PasswordChangeState> = _state.asStateFlow()

    private val _uiState = MutableStateFlow(PasswordSettingsUiState())
    val uiState: StateFlow<PasswordSettingsUiState> = _uiState.asStateFlow()

    override fun getIdleState() = PasswordChangeState.Idle
    override fun getLoadingState() = PasswordChangeState.Loading
    override fun getErrorState(message: String) = PasswordChangeState.Error(message)

    fun onCurrentPasswordChange(pw: String) {
        if (pw.length <= 32) {
            _uiState.update { it.copy(currentPassword = pw) }
            clearError()
        }
    }

    fun onNewPasswordChange(pw: String) {
        if (pw.length <= 32) {
            _uiState.update { it.copy(newPassword = pw) }
            clearError()
        }
    }

    fun onConfirmPasswordChange(pw: String) {
        if (pw.length <= 32) {
            _uiState.update { it.copy(confirmPassword = pw) }
            clearError()
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
        val validationError = validatePasswords()
        if (validationError != null) {
            _state.value = PasswordChangeState.Error(validationError)
            _uiState.update { it.copy(errorMessage = validationError) }
            return
        }

        executeAsync(showLoading = true) {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            delay(1200)
            _state.value = PasswordChangeState.Success
            _uiState.update { it.copy(isLoading = false, isSuccess = true) }
        }
    }

    private fun validatePasswords(): String? {
        val state = _uiState.value
        
        return when {
            state.currentPassword.length < PASSWORD_MIN_LEN -> 
                resourceProvider.getString(R.string.error_current_password_length)
            
            state.newPassword.length < PASSWORD_MIN_LEN -> 
                resourceProvider.getString(R.string.error_new_password_length)
            
            state.newPassword != state.confirmPassword -> 
                resourceProvider.getString(R.string.error_passwords_dont_match)
            
            state.currentPassword == state.newPassword -> 
                resourceProvider.getString(R.string.error_password_same_as_current)
            
            else -> null
        }
    }

    fun clearError() {
        if (_state.value is PasswordChangeState.Error) {
            _state.value = PasswordChangeState.Idle
        }
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun resetSuccess() {
        _state.value = PasswordChangeState.Idle
        _uiState.update { it.copy(isSuccess = false) }
    }
}
