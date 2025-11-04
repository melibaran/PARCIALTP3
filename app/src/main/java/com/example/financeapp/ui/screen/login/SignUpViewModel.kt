package com.example.financeapp.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.R
import com.example.financeapp.core.ResourceProvider
import com.example.financeapp.domain.infrastructure.api.ApiClient
import com.example.financeapp.domain.model.SignUpRequest as DomainSignUpRequest
import com.example.financeapp.domain.model.User
import com.example.financeapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val apiClient: ApiClient,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState.asStateFlow()

    fun signUp(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            try {
                _signUpState.value = SignUpState.Loading

                if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
                    _signUpState.value = SignUpState.Error(
                        resourceProvider.getString(R.string.error_all_fields_required)
                    )
                    return@launch
                }

                if (password != confirmPassword) {
                    _signUpState.value = SignUpState.Error(
                        resourceProvider.getString(R.string.error_passwords_mismatch)
                    )
                    return@launch
                }

                if (password.length < 6) {
                    _signUpState.value = SignUpState.Error(
                        resourceProvider.getString(R.string.error_password_min_length)
                    )
                    return@launch
                }

                val existingUser = userRepository.getUserByEmail(email)
                if (existingUser != null) {
                    _signUpState.value = SignUpState.Error(
                        resourceProvider.getString(R.string.error_email_already_in_use)
                    )
                    return@launch
                }

                val nameParts = fullName.trim().split(" ", limit = 2)
                val firstName = nameParts.getOrNull(0) ?: ""
                val lastName = nameParts.getOrNull(1) ?: ""

                val newUser = User(
                    email = email,
                    firstName = firstName,
                    lastName = lastName,
                    password = password
                )

                val userId = userRepository.insertUser(newUser)

                if (userId > 0) {
                    val signUpRequest = DomainSignUpRequest(
                        id = userId.toInt(),
                        username = fullName,
                        email = email,
                        password = password
                    )
                    val userProfile = apiClient.signUp(signUpRequest)

                    _signUpState.value = SignUpState.Success(
                        userId = userId.toInt(),
                        email = email,
                        fullName = fullName,
                        userProfile = userProfile
                    )
                } else {
                    _signUpState.value = SignUpState.Error(
                        resourceProvider.getString(R.string.error_create_user_failed)
                    )
                }

            } catch (e: Exception) {
                _signUpState.value = SignUpState.Error(
                    e.message ?: resourceProvider.getString(R.string.error_unknown_signup)
                )
            }
        }
    }

    fun resetState() {
        _signUpState.value = SignUpState.Idle
    }
}

sealed class SignUpState {
    data object Idle : SignUpState()
    data object Loading : SignUpState()
    data class Success(
        val userId: Int,
        val email: String,
        val fullName: String,
        val userProfile: com.example.financeapp.domain.model.UserProfile? = null
    ) : SignUpState()
    data class Error(val message: String) : SignUpState()
}

