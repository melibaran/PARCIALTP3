package com.example.financeapp.ui.mapper

import com.example.financeapp.R
import com.example.financeapp.domain.model.Transaction
import com.example.financeapp.ui.screen.transaction.TransactionItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object TransactionMapper {
    
    private val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val monthFormat = SimpleDateFormat("MMMM", Locale.ENGLISH)

    fun toTransactionItem(transaction: Transaction): TransactionItem {
        val date = parseDate(transaction.date)
        val month = extractMonth(date)
        val dateTimeFormatted = formatDateTime(date)
        val isIncome = isIncomeTransaction(transaction.type)
        val amount = if (isIncome) transaction.amount else -transaction.amount
        val cleanedDescription = cleanDescription(transaction.description)
        
        return TransactionItem(
            id = transaction.transactionId,
            title = cleanedDescription,
            amount = amount,
            category = transaction.subtype,
            dateTime = dateTimeFormatted,
            iconId = getIconForTransaction(transaction.subtype, transaction.type),
            isIncome = isIncome,
            month = month
        )
    }
    
    private fun parseDate(dateString: String): Date {
        return try {
            inputDateFormat.parse(dateString) ?: Date()
        } catch (e: Exception) {
            try {
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(dateString) ?: Date()
            } catch (e: Exception) {
                Date()
            }
        }
    }
    
    private fun extractMonth(date: Date): String {
        return monthFormat.format(date)
    }
    
    private fun formatDateTime(date: Date): String {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val monthDayFormat = SimpleDateFormat("MMMM dd", Locale.ENGLISH)
        
        val time = timeFormat.format(date)
        val monthDay = monthDayFormat.format(date)
        
        return "$time - $monthDay"
    }
    
    private fun isIncomeTransaction(type: String): Boolean {
        return type.lowercase() in listOf("income", "ingreso", "deposit", "salary")
    }
    
    private fun cleanDescription(description: String): String {
        var cleaned = description.trim()
        
        val prefixesToRemove = listOf(
            "devolución de compra en ",
            "devolución de compra de ",
            "devolución en ",
            "devolución de ",
            "compra en ",
            "compra de ",
            "pago en ",
            "pago de ",
            "transferencia a ",
            "transferencia de ",
            "deposito en ",
            "deposito de ",
            "depósito en ",
            "depósito de ",
            "retiro en ",
            "retiro de "
        )
        
        for (prefix in prefixesToRemove) {
            if (cleaned.lowercase().startsWith(prefix)) {
                cleaned = cleaned.substring(prefix.length)
                break
            }
        }
        
        return cleaned.replaceFirstChar { 
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) 
            else it.toString() 
        }
    }
    
    private fun getIconForTransaction(subtype: String, type: String): Int {
        val subtypeLower = subtype.lowercase()
        val typeLower = type.lowercase()
        
        return when (subtypeLower) {
            "food" -> R.drawable.food
            "clothes" -> R.drawable.groceries
            "services" -> R.drawable.rent
            "savings" -> R.drawable.salary
            else -> if (typeLower == "income") R.drawable.income else R.drawable.expense
        }
    }
    
    fun toTransactionItems(transactions: List<Transaction>): List<TransactionItem> {
        return transactions.map { toTransactionItem(it) }
    }
}

