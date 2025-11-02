package com.example.financeapp.ui.screen.savings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.CategoryItem
import com.example.financeapp.ui.screen.categories.arquitectura.BaseFinanceScreen
import com.example.financeapp.ui.screen.categories.arquitectura.FinanceDisplayData
import com.example.financeapp.ui.screen.categories.arquitectura.FinanceGridContainer
import com.example.financeapp.ui.screen.categories.savings.SavingsViewModel
import com.example.financeapp.ui.theme.*

@Composable
fun SavingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SavingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    BaseFinanceScreen(
        title = "Savings",
        navController = navController,
        data = FinanceDisplayData(
            balance = uiState.balance.toDoubleOrNull() ?: 0.0,
            totalExpense = uiState.totalExpense.toDoubleOrNull() ?: 0.0,
            budget = uiState.budget.toDoubleOrNull() ?: 0.0,
            expensePercentage = uiState.expensePercentage
        ),
        modifier = modifier,
        topContent = null, // Savings no tiene el card superior
        progressBarContent = { data ->
            // Progress bar personalizado para Savings con barra negra
            Column(
                modifier = Modifier.padding(horizontal = 21.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(28.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Honeydew)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(20.dp))
                                .fillMaxWidth(data.expensePercentage / 100f)
                                .background(Void)
                        )
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${data.expensePercentage}%",
                                modifier = Modifier.padding(start = 12.dp),
                                color = Honeydew,
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontSize = 13.sp,
                                )
                            )
                            Text(
                                text = "${data.budget}",
                                modifier = Modifier.padding(end = 12.dp),
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    color = Void,
                                    fontSize = 13.sp,
                                )
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.check),
                        contentDescription = "Check Icon",
                        tint = Void,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${data.expensePercentage}% Of Your Expenses, Looks Good.",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Void,
                            fontSize = 14.sp,
                        )
                    )
                }
            }
        },
        bottomContent = {
            FinanceGridContainer {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.savingsGoals) { goal ->
                        CategoryItem(
                            icon = goal.iconId,
                            name = goal.title,
                            onClick = { /* TODO: Navigate to goal details */ }
                        )
                    }
                }

                // Add More Button
                Button(
                    onClick = { /* TODO: Add more savings goals */ },
                    modifier = Modifier
                        .padding(horizontal = 60.dp, vertical = 24.dp)
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Caribbean_green
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Add More",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                            fontSize = 16.sp,
                            color = Void
                        )
                    )
                }
            }
        }
    )
}