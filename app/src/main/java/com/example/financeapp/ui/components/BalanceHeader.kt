package com.example.financeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.financeapp.R
import com.example.financeapp.ui.theme.Fence_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.LocalThemeController
import com.example.financeapp.ui.theme.Ocean_blue
import java.text.NumberFormat
import java.util.Locale

@Composable
fun BalanceHeader(
    totalBalance: Double,
    totalExpense: Double,
    budget: Double,
    progressPercentage: Int,
    modifier: Modifier = Modifier,
    progressText: String = "$progressPercentage%"
) {
    val colorScheme = MaterialTheme.colorScheme
    val themeController = LocalThemeController.current
    val isDark = themeController.isDarkMode
    val formatter = NumberFormat.getNumberInstance(Locale.US).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(12.dp))

        // Total Balance y Total Expense Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_up),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.total_balance),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = colorScheme.onBackground
                        )
                    )
                }
                Text(
                    text = "$${formatter.format(totalBalance)}",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.onBackground
                    )
                )
            }

            VerticalDivider(
                modifier = Modifier
                    .height(40.dp)
                    .padding(horizontal = 14.dp),
                thickness = 1.dp,
                color = colorScheme.surfaceVariant
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_down),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.total_expense),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = colorScheme.onBackground
                        )
                    )
                }
                Text(
                    text = "-$${formatter.format(totalExpense)}",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Ocean_blue
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Progress Bar
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(colorScheme.surface)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .fillMaxWidth(progressPercentage / 100f)
                            .background(if (isDark) MaterialTheme.colorScheme.primary else Color.Black)
                    )
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = progressText,
                            modifier = Modifier.padding(start = 8.dp),
                            color = if (isDark) MaterialTheme.colorScheme.onPrimary else Color.White,
                            style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            text = "$${formatter.format(budget)}",
                            modifier = Modifier.padding(end = 8.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = if (isDark) MaterialTheme.colorScheme.onBackground else Fence_green
                            )
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "Check",
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.goal_description),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = if (isDark) MaterialTheme.colorScheme.onBackground else Fence_green
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
