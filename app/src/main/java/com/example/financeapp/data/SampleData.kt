package com.example.financeapp.data

import androidx.annotation.DrawableRes
import com.example.financeapp.R

data class Transaction(
    @DrawableRes val iconResId: Int,
    val titleResId: Int,
    val dateResId: Int,
    val categoryResId: Int,
    val amount: Double,
    val isExpense: Boolean
)

val sampleTransactions: List<Transaction> = listOf(
    Transaction(R.drawable.salary, R.string.salary, R.string.date_apr_30, R.string.monthly, 4000.00, false),
    Transaction(R.drawable.groceries_default, R.string.groceries, R.string.date_apr_24, R.string.pantry, -100.00, true),
    Transaction(R.drawable.rent_default, R.string.rent, R.string.date_apr_15, R.string.rent, -674.40, true),
    Transaction(R.drawable.car, R.string.transport, R.string.date_apr_08, R.string.fuel, -4.13, true),
    Transaction(R.drawable.food_default, R.string.food, R.string.date_apr_28, R.string.dinner, -45.50, true),
    Transaction(R.drawable.entertaiment_default, R.string.entertainment, R.string.date_apr_22, R.string.movie, -25.00, true),
    Transaction(R.drawable.gift_default, R.string.gift, R.string.date_apr_20, R.string.birthday, -75.00, true)
)

