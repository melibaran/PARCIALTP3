package com.example.financeapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp


data class BottomNavItem(
    @DrawableRes val icon: Int,
    val label: String,
    val route: String,
    val width: Dp,
    val height: Dp
)
