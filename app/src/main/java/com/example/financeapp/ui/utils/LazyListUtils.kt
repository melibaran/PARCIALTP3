package com.example.financeapp.ui.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

/**
 * Extension function para detectar scroll al final de la lista
 * Útil para implementar paginación infinita
 *
 * @param buffer Número de ítems antes del final para disparar el evento
 * @return Boolean indicando si se llegó al final
 */
@Composable
fun LazyListState.isScrolledToEnd(buffer: Int = 3): Boolean {
    return remember(this) {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf false

            lastVisibleItem.index >= layoutInfo.totalItemsCount - buffer
        }
    }.value
}

/**
 * Composable para manejar carga paginada de forma declarativa
 *
 * @param loadMore Callback para cargar más datos
 * @param isLoading Estado de carga actual
 */
@Composable
fun LazyListState.OnBottomReached(
    buffer: Int = 3,
    isLoading: Boolean = false,
    loadMore: () -> Unit
) {
    val shouldLoadMore = isScrolledToEnd(buffer)

    LaunchedEffect(shouldLoadMore, isLoading) {
        if (shouldLoadMore && !isLoading) {
            loadMore()
        }
    }
}

