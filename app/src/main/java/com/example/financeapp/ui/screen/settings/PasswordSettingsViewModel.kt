package com.example.financeapp.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.core.ResourceProvider
import com.example.financeapp.domain.repository.UserRepository
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
    private val resourceProvider: ResourceProvider,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PasswordSettingsUiState())
    val uiState: StateFlow<PasswordSettingsUiState> = _uiState.asStateFlow()
 
    private var currentUserEmail: String = ""

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

    fun setCurrentUser(email: String) {
        currentUserEmail = email
    }
    
    fun changePassword() {
        val state = _uiState.value

        if (state.currentPassword.length < PASSWORD_MIN_LEN) {
            _uiState.update { 
                it.copy(errorMessage = resourceProvider.getString(
                    com.example.financeapp.R.string.error_current_password_length
                )) 
            }
            return
        }

        if (state.newPassword.length < PASSWORD_MIN_LEN) {
            _uiState.update { 
                it.copy(errorMessage = resourceProvider.getString(
                    com.example.financeapp.R.string.error_new_password_length
                )) 
            }
            return
        }

        if (state.newPassword != state.confirmPassword) {
            _uiState.update { 
                it.copy(errorMessage = resourceProvider.getString(
                    com.example.financeapp.R.string.error_passwords_dont_match
                )) 
            }
            return
        }

        if (state.currentPassword == state.newPassword) {
            _uiState.update { 
                it.copy(errorMessage = resourceProvider.getString(
                    com.example.financeapp.R.string.error_password_same_as_current
                )) 
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val user = userRepository.getUserByEmail(currentUserEmail)
                
                if (user == null) {
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            errorMessage = resourceProvider.getString(
                                com.example.financeapp.R.string.error_user_not_found_signin_again
                            )
                        ) 
                    }
                    return@launch
                }

                if (user.password != state.currentPassword) {
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            errorMessage = resourceProvider.getString(
                                com.example.financeapp.R.string.error_current_password_incorrect
                            )
                        ) 
                    }
                    return@launch
                }

                val rowsAffected = userRepository.updatePassword(currentUserEmail, state.newPassword)
                
                if (rowsAffected > 0) {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                } else {
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            errorMessage = resourceProvider.getString(
                                com.example.financeapp.R.string.error_update_password_failed
                            )
                        ) 
                    }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        errorMessage = e.message ?: resourceProvider.getString(
                            com.example.financeapp.R.string.error_change_password_failed
                        )
                    ) 
                }
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
