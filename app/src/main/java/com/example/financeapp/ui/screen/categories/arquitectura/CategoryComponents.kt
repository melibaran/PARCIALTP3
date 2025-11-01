package com.example.financeapp.ui.screen.categories.arquitectura

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@Composable
fun CategoryBalanceExpenseRow(
    balance: Double,
    totalExpense: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Total Balance
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.income),
                    contentDescription = "Balance Icon",
                    modifier = Modifier.size(16.dp),
                    tint = Honeydew
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Total Balance",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Honeydew,
                        fontSize = 12.sp,
                    )
                )
            }
            Text(
                text = "$${String.format(Locale.US, "%.2f", balance)}",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    color = Honeydew,
                    fontSize = 26.sp,
                )
            )
        }

        VerticalDivider(
            modifier = Modifier
                .height(48.dp)
                .padding(horizontal = 14.dp),
            thickness = 1.dp,
            color = Light_green
        )

        // Total Expense
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.expense),
                    contentDescription = "Expense Icon",
                    modifier = Modifier.size(16.dp),
                    tint = Honeydew
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Total Expense",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Honeydew,
                        fontSize = 12.sp,
                    )
                )
            }
            Text(
                text = "$${String.format(Locale.US, "%.2f", totalExpense)}",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    fontSize = 26.sp,
                    color = Ocean_blue
                )
            )
        }
    }
}

@Composable
fun CategoryProgressBar(
    expensePercentage: Int,
    budget: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 21.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Honeydew)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth(expensePercentage / 100f)
                        .background(Fence_green)
                )
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$expensePercentage%",
                        modifier = Modifier.padding(start = 8.dp),
                        color = Honeydew,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontSize = 12.sp,
                        )
                    )
                    Text(
                        text = "$${String.format(Locale.US, "%.2f", budget)}",
                        modifier = Modifier.padding(end = 8.dp),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            color = Fence_green,
                            fontSize = 12.sp,
                        )
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.check),
                contentDescription = "Check Icon",
                tint = Void,
                modifier = Modifier.size(13.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$expensePercentage% Of Your Expenses, Looks Good.",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Fence_green,
                    fontSize = 14.sp,
                )
            )
        }
    }
}

//AddExpressBottom
@Composable
fun CategoryAddExpensesButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Honeydew)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .width(169.dp)
                .height(36.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Caribbean_green
            ),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text(
                text = "Add Expenses",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    color = Honeydew,
                    fontSize = 16.sp,
                )
            )
        }
    }
}

