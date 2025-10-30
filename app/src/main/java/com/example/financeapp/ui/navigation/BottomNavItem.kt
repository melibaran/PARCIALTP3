package com.example.financeapp.ui.navigation

import androidx.annotation.DrawableRes

data class BottomNavItem(
    @DrawableRes val icon: Int,
    val label: String,
    val route: String
)
