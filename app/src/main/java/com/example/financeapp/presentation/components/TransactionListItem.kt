package com.example.financeapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.financeapp.presentation.theme.Caribbean_green
import com.example.financeapp.presentation.theme.Light_blue
import com.example.financeapp.presentation.screen.transaction.TransactionItem

@Composable
fun TransactionListItem(
    transaction: TransactionItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(
                    if (transaction.isIncome) Caribbean_green.copy(alpha = 0.2f)
                    else Light_blue.copy(alpha = 0.2f)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = transaction.iconId),
                contentDescription = null,
                tint = if (transaction.isIncome) Caribbean_green else Light_blue,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = transaction.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = transaction.dateTime,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Light_blue
                )
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = transaction.category,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = formatAmount(transaction.amount),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = if (transaction.isIncome) Caribbean_green else Light_blue
                )
            )
        }
    }
}

private fun formatAmount(amount: Double): String {
    return if (amount >= 0) "$$amount" else "-$${-amount}"
}
