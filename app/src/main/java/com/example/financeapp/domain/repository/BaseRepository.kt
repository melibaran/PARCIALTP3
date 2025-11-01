package com.example.financeapp.domain.repository

import com.example.financeapp.domain.model.UiState
import kotlinx.coroutines.flow.Flow

/**
 * Interface base para repositorios
 * Patrón: Repository Pattern
 *
 * Ventajas:
 * - Abstracción de la fuente de datos (API, DB, Cache)
 * - Testeable (se puede mockear fácilmente)
 * - Single Source of Truth
 */
interface BaseRepository<T> {
    /**
     * Obtiene todos los elementos
     */
    suspend fun getAll(): Flow<UiState<List<T>>>

    /**
     * Obtiene un elemento por ID
     */
    suspend fun getById(id: String): Flow<UiState<T>>

    /**
     * Inserta o actualiza un elemento
     */
    suspend fun save(item: T): Flow<UiState<T>>

    /**
     * Elimina un elemento
     */
    suspend fun delete(id: String): Flow<UiState<Unit>>
}

/**
 * Ejemplo de repositorio para transacciones
 */
interface TransactionRepository {
    suspend fun getTransactionsByCategory(category: String): Flow<UiState<List<Any>>>
    suspend fun getTransactionsByMonth(month: String): Flow<UiState<List<Any>>>
    suspend fun addTransaction(transaction: Any): Flow<UiState<Unit>>
    suspend fun deleteTransaction(id: String): Flow<UiState<Unit>>
}

