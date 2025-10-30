package com.example.financeapp.domain.model

data class UserTransactions(
    val userId: Int,
    val balance: Double,
    val income: Double,
    val expense: Double,
    val transactions: List<Transaction>
)

