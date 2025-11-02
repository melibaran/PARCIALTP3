package com.example.financeapp.ui.screen.categories.arquitectura

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.ui.theme.*
import java.util.Locale

data class CategoryTransactionItem(
    val id: String,
    val title: String,
    val dateTime: String,
    val amount: Double,
    val iconId: Int,
    val month: String
)

@Composable
fun CategoryTransactionList(
    transactions: List<CategoryTransactionItem>,
    availableMonths: List<String>,
    onDatePickerClick: () -> Unit,
    modifier: Modifier = Modifier,
    circleColors: List<Color> = listOf(
        Light_blue,
        Vivid_blue,
        Ocean_blue,
        Vivid_blue,
        Light_blue
    )
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
            .background(Honeydew)
            .padding(16.dp)
    ) {
        var globalIndex = 0
        var displayedMonthCount = 0

        availableMonths.forEach { month ->
            val monthTransactions = transactions.filter { it.month == month }

            if (monthTransactions.isNotEmpty()) {
                val isFirstDisplayedMonth = displayedMonthCount == 0

                item(key = "header_$month") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 8.dp,
                                top = if (isFirstDisplayedMonth) 0.dp else 8.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            month,
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                color = Fence_green,
                                fontSize = 20.sp,
                            ),
                            modifier = Modifier.weight(1f)
                        )

                        if (isFirstDisplayedMonth) {
                            val calendarInteractionSource = remember { MutableInteractionSource() }
                            val isCalendarPressed by calendarInteractionSource.collectIsPressedAsState()

                            val calendarIcon = if (isCalendarPressed) {
                                R.drawable.calendar_pressed
                            } else {
                                R.drawable.calendar_default
                            }

                            IconButton(
                                onClick = onDatePickerClick,
                                modifier = Modifier
                                    .size(32.26.dp, 30.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                interactionSource = calendarInteractionSource
                            ) {
                                Icon(
                                    painter = painterResource(calendarIcon),
                                    contentDescription = "Calendar",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }

                itemsIndexed(
                    monthTransactions,
                    key = { _, item -> item.id }
                ) { localIndex, transaction ->
                    val itemColor = circleColors.getOrElse(globalIndex + localIndex) { Light_blue }

                    CategoryTransactionListItem(
                        transaction = transaction,
                        circleBgColor = itemColor
                    )
                }

                globalIndex += monthTransactions.size
                displayedMonthCount++
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun CategoryTransactionListItem(
    transaction: CategoryTransactionItem,
    circleBgColor: Color,
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
                .background(circleBgColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = transaction.iconId),
                contentDescription = transaction.title,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = transaction.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    color = Void
                )
            )
            Text(
                text = transaction.dateTime,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Ocean_blue,
                    fontSize = 11.sp,
                )
            )
        }

        Text(
            text = "-$${String.format(Locale.US, "%.2f", transaction.amount)}",
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                color = Ocean_blue
            )
        )
    }
}

