package com.example.financeapp.infrastructure.api.response

import com.example.financeapp.domain.model.Transaction as DomainTransaction
import com.example.financeapp.domain.model.UserTransactions

data class TransactionsResponse(
    val user_id: Int,
    val balance: Double,
    val income: Double,
    val expense: Double,
    val transactions: List<Transaction>
) {
    fun toDomain(): UserTransactions {
        return UserTransactions(
            userId = user_id,
            balance = balance,
            income = income,
            expense = expense,
            transactions = transactions.map { it.toDomain() }
        )
    }
}

data class Transaction(
    val transaction_id: String,
    val date: String,
    val description: String,
    val amount: Double,
    val currency: String,
    val type: String,
    val subtype: String
) {
    fun toDomain(): DomainTransaction {
        return DomainTransaction(
            transactionId = transaction_id,
            date = date,
            description = description,
            amount = amount,
            currency = currency,
            type = type,
            subtype = subtype
        )
    }
}
