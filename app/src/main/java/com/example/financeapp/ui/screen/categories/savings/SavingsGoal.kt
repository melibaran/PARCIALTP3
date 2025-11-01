package com.example.financeapp.ui.screen.categories.savings

import androidx.compose.ui.graphics.painter.Painter

data class SavingsGoal(
    val title: String,
    val painter: Painter,
    val goalAmount: Double,
    val savedAmount: Double,
    val progressPercentage: Int,
    val deposits: Map<String, List<Deposit>>,
    val route: String = "" // Ruta de navegación para este objetivo
)
