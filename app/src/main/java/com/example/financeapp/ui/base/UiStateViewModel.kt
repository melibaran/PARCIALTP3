package com.example.financeapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.core.ResourceProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class UiStateViewModel<S>(
    protected val resourceProvider: ResourceProvider
) : ViewModel() {

    protected abstract val _state: MutableStateFlow<S>
    abstract val state: StateFlow<S>

    protected abstract fun getIdleState(): S
    protected abstract fun getLoadingState(): S
    protected abstract fun getErrorState(message: String): S

    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }

    protected fun executeAsync(
        showLoading: Boolean = true,
        onError: ((Exception) -> Unit)? = null,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(exceptionHandler) {
            try {
                if (showLoading) {
                    _state.value = getLoadingState()
                }
                block()
            } catch (e: Exception) {
                onError?.invoke(e) ?: handleException(e)
            }
        }
    }

    protected open fun handleException(exception: Throwable) {
        val message = exception.message ?: "Unknown error occurred"
        _state.value = getErrorState(message)
    }

    open fun resetState() {
        _state.value = getIdleState()
    }
}

