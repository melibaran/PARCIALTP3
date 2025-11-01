package com.example.financeapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.domain.model.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel base que proporciona funcionalidad común
 * Patrón: Template Method
 *
 * Ventajas:
 * - Manejo consistente de errores
 * - Loading states automáticos
 * - Reducción de código duplicado
 */
abstract class BaseViewModel<T> : ViewModel() {

    protected val _uiState = MutableStateFlow<UiState<T>>(UiState.Loading)
    val uiState: StateFlow<UiState<T>> = _uiState.asStateFlow()

    /**
     * Exception handler para errores en coroutines
     */
    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = UiState.Error(
            message = exception.message ?: "Unknown error",
            throwable = exception
        )
    }

    /**
     * Ejecuta una operación con manejo automático de loading y errores
     *
     * @param showLoading Si debe mostrar estado de carga
     * @param block Operación a ejecutar
     */
    protected fun launchWithState(
        showLoading: Boolean = true,
        block: suspend () -> T
    ) {
        viewModelScope.launch(exceptionHandler) {
            if (showLoading) {
                _uiState.value = UiState.Loading
            }

            try {
                val result = block()
                _uiState.value = UiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    message = e.message ?: "Error desconocido",
                    throwable = e
                )
            }
        }
    }

    /**
     * Actualiza el estado con datos exitosos
     */
    protected fun setSuccess(data: T) {
        _uiState.value = UiState.Success(data)
    }

    /**
     * Actualiza el estado con error
     */
    protected fun setError(message: String, throwable: Throwable? = null) {
        _uiState.value = UiState.Error(message, throwable)
    }
}

