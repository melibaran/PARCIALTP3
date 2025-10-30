package com.example.financeapp.infrastructure.api.response

data class TransactionsResponse(
    val user_id: Int,
    val balance: Double,
    val income: Double,
    val expense: Double,
    val transactions: List<Transaction>
)

data class Transaction(
    val transaction_id: String,
    val date: String,
    val description: String,
    val amount: Double,
    val currency: String,
    val type: String,
    val subtype: String
)

