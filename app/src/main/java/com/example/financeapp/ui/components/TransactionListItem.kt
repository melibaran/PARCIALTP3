package com.example.financeapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.financeapp.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.screen.transaction.TransactionItem
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Ocean_blue
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Fence_green
import com.example.financeapp.ui.theme.LocalDarkMode

@Composable
fun TransactionListItem(
    transaction: TransactionItem,
    circleBgColor: Color,
    modifier: Modifier = Modifier
) {
    val darkModeState = LocalDarkMode.current
    val isDarkMode = darkModeState.isDarkMode
    val circleColor = Light_blue
    val iconTint = Honeydew
    val dividerColor = if (isDarkMode) Light_green else Caribbean_green

    val amountColor = if (transaction.isIncome) {
        if (isDarkMode) Color.White else Void
    } else Ocean_blue

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. CÍRCULO Y ÍCONO
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(circleBgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = transaction.iconId),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // 2. COLUMNA DE TÍTULO Y FECHA/HORA
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = transaction.title,
                style = MaterialTheme.typography.titleMedium.copy(color = if (isDarkMode) Color.White else Void)
            )
            Text(
                text = transaction.dateTime,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    color = Ocean_blue,
                    fontSize = 11.sp,
                )
            )
        }

        VerticalDivider(
            modifier = Modifier.height(40.dp).padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = dividerColor
        )

        Column(
            modifier = Modifier.weight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = transaction.category,
                style = MaterialTheme.typography.bodyMedium.copy(color = if (isDarkMode) Color.White else Void)
            )
        }

        VerticalDivider(
            modifier = Modifier.height(40.dp).padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = dividerColor
        )

        Column(
            modifier = Modifier.weight(0.8f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = formatAmount(transaction.amount),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = amountColor
                )
            )
        }
    }
}

private fun formatAmount(amount: Double): String {
    return if (amount >= 0) "$$amount" else "-$${-amount}"
}