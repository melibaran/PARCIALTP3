package com.example.financeapp.domain.model


data class Transaction(
    val transactionId: String,
    val date: String,
    val description: String,
    val amount: Double,
    val currency: String,
    val type: String,
    val subtype: String
)

