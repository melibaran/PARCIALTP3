package com.example.financeapp.ui.screen.login

import com.example.financeapp.R
import com.example.financeapp.core.ResourceProvider
import com.example.financeapp.domain.repository.UserRepository
import com.example.financeapp.ui.base.UiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(
        val userId: Int,
        val email: String,
        val fullName: String
    ) : LoginState()
    data class Error(val message: String) : LoginState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    resourceProvider: ResourceProvider
) : UiStateViewModel<LoginState>(resourceProvider) {

    override val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    override val state: StateFlow<LoginState> = _state.asStateFlow()

    val loginState: StateFlow<LoginState> = state

    override fun getIdleState() = LoginState.Idle
    override fun getLoadingState() = LoginState.Loading
    override fun getErrorState(message: String) = LoginState.Error(message)

    fun login(email: String, password: String) {
        executeAsync {
            val user = userRepository.getUserByEmail(email)
                ?: run {
                    _state.value = LoginState.Error(
                        resourceProvider.getString(R.string.error_user_not_found)
                    )
                    return@executeAsync
                }

            if (user.password != password) {
                _state.value = LoginState.Error(
                    resourceProvider.getString(R.string.error_incorrect_password)
                )
                return@executeAsync
            }

            _state.value = LoginState.Success(
                userId = user.id,
                email = user.email,
                fullName = "${user.firstName} ${user.lastName}"
            )
        }
    }
}



