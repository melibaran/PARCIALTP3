package com.example.financeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.financeapp.R
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.theme.Light_blue

@Composable
fun BalanceHeader(
    totalBalance: Float,
    totalExpense: Float,
    budget: Float
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = stringResource(id = R.string.total_balance), style = MaterialTheme.typography.bodyMedium)
        Text(text = "$$totalBalance", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.total_expense), style = MaterialTheme.typography.bodyMedium)
            Text(text = "-$$totalExpense", style = MaterialTheme.typography.bodyMedium, color = Light_blue)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = totalExpense / budget,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.goal_description), style = MaterialTheme.typography.bodySmall)
            Text(text = "$$budget", style = MaterialTheme.typography.bodySmall)
        }
    }
}
