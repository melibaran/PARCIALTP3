package com.example.financeapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.financeapp.domain.model.Category

@Composable
fun CategoriesGrid(categories: List<Category>) {
    val config = LocalConfiguration.current
    val columns = when {
        config.screenWidthDp < 600 -> 2
        config.screenWidthDp < 840 -> 3
        else -> 4
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.padding(16.dp)
    ) {
        items(categories) { category ->
            CategoryItem(icon = category.icon, name = category.name)
        }
    }
}
