package com.example.financeapp.ui.screen.login

import com.example.financeapp.R
import com.example.financeapp.core.ResourceProvider
import com.example.financeapp.domain.model.User
import com.example.financeapp.domain.repository.UserRepository
import com.example.financeapp.ui.base.UiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

sealed class SignUpState {
    data object Idle : SignUpState()
    data object Loading : SignUpState()
    data class Success(
        val userId: Int,
        val email: String,
        val fullName: String
    ) : SignUpState()
    data class Error(val message: String) : SignUpState()
}

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository,
    resourceProvider: ResourceProvider
) : UiStateViewModel<SignUpState>(resourceProvider) {

    override val _state = MutableStateFlow<SignUpState>(SignUpState.Idle)
    override val state: StateFlow<SignUpState> = _state.asStateFlow()

    val signUpState: StateFlow<SignUpState> = state

    override fun getIdleState() = SignUpState.Idle
    override fun getLoadingState() = SignUpState.Loading
    override fun getErrorState(message: String) = SignUpState.Error(message)

    fun signUp(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        executeAsync {
            validateInputs(fullName, email, password, confirmPassword)
                ?: return@executeAsync

            checkEmailAvailability(email)
                ?: return@executeAsync

            val user = createUser(fullName, email, password)
            val userId = userRepository.insertUser(user)

            if (userId > 0) {
                _state.value = SignUpState.Success(
                    userId = userId.toInt(),
                    email = email,
                    fullName = fullName
                )
            } else {
                _state.value = SignUpState.Error(
                    resourceProvider.getString(R.string.error_create_user_failed)
                )
            }
        }
    }

    private suspend fun validateInputs(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Unit? {
        when {
            fullName.isBlank() || email.isBlank() || password.isBlank() -> {
                _state.value = SignUpState.Error(
                    resourceProvider.getString(R.string.error_all_fields_required)
                )
                return null
            }
            password != confirmPassword -> {
                _state.value = SignUpState.Error(
                    resourceProvider.getString(R.string.error_passwords_mismatch)
                )
                return null
            }
            password.length < 6 -> {
                _state.value = SignUpState.Error(
                    resourceProvider.getString(R.string.error_password_min_length)
                )
                return null
            }
        }
        return Unit
    }

    private suspend fun checkEmailAvailability(email: String): Unit? {
        val existingUser = userRepository.getUserByEmail(email)
        return if (existingUser != null) {
            _state.value = SignUpState.Error(
                resourceProvider.getString(R.string.error_email_already_in_use)
            )
            null
        } else {
            Unit
        }
    }

    private fun createUser(fullName: String, email: String, password: String): User {
        val nameParts = fullName.trim().split(" ", limit = 2)
        return User(
            email = email,
            firstName = nameParts.getOrNull(0) ?: "",
            lastName = nameParts.getOrNull(1) ?: "",
            password = password
        )
    }
}

