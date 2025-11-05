package com.example.financeapp.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.R
import com.example.financeapp.core.ResourceProvider
import com.example.financeapp.domain.infrastructure.api.ApiClient
import com.example.financeapp.domain.model.LoginRequest as DomainLoginRequest
import com.example.financeapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val apiClient: ApiClient,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loginState.value = LoginState.Loading

                val user = userRepository.getUserByEmail(email)

                if (user == null) {
                    _loginState.value = LoginState.Error(
                        resourceProvider.getString(R.string.error_user_not_found)
                    )
                    return@launch
                }

                if (user.password != password) {
                    _loginState.value = LoginState.Error(
                        resourceProvider.getString(R.string.error_incorrect_password)
                    )
                    return@launch
                }

                val loginRequest = DomainLoginRequest(
                    username = email,
                    password = password
                )
                val userToken = apiClient.login(loginRequest)

                _loginState.value = LoginState.Success(
                    userId = user.id,
                    email = user.email,
                    fullName = "${user.firstName} ${user.lastName}",
                    token = userToken.token
                )

            } catch (e: Exception) {
                _loginState.value = LoginState.Error(
                    e.message ?: resourceProvider.getString(R.string.error_unknown_login)
                )
            }
        }
    }

    fun resetState() {
        _loginState.value = LoginState.Idle
    }
}

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(
        val userId: Int,
        val email: String,
        val fullName: String,
        val token: String? = null
    ) : LoginState()
    data class Error(val message: String) : LoginState()
}

