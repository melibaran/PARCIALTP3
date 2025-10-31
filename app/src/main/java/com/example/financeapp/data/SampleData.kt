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
    Transaction(R.drawable.groceries, R.string.groceries, R.string.date_apr_24, R.string.pantry, -100.00, true),
    Transaction(R.drawable.rent, R.string.rent, R.string.date_apr_15, R.string.rent, -674.40, true),
    Transaction(R.drawable.car, R.string.transport, R.string.date_apr_08, R.string.fuel, -4.13, true)
)

val foodTransactions: Map<Int, List<Transaction>> = mapOf(
    R.string.april to listOf(
        Transaction(R.drawable.food, R.string.dinner, R.string.date_apr_30, 0, -26.00, true),
        Transaction(R.drawable.food, R.string.delivery_pizza, R.string.date_apr_24_delivery, 0, -18.35, true),
        Transaction(R.drawable.food, R.string.lunch, R.string.date_apr_15_lunch, 0, -15.40, true),
        Transaction(R.drawable.food, R.string.brunch, R.string.date_apr_08, 0, -12.13, true)
    ),
    R.string.march to listOf(
        Transaction(R.drawable.food, R.string.dinner, R.string.date_mar_31, 0, -27.20, true)
    )
)
