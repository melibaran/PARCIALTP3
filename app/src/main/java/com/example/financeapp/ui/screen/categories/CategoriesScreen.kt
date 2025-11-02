package com.example.financeapp.ui.screen.categories

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.CategoryItem
import com.example.financeapp.ui.screen.categories.arquitectura.BaseFinanceScreen
import com.example.financeapp.ui.screen.categories.arquitectura.DefaultProgressBar
import com.example.financeapp.ui.screen.categories.arquitectura.FinanceDisplayData
import com.example.financeapp.ui.theme.*
import com.example.financeapp.ui.screen.categories.arquitectura.FinanceGridContainer

@Composable
fun CategoriesScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    BaseFinanceScreen(
        title = "Categories",
        navController = navController,
        data = FinanceDisplayData(
            balance = uiState.balance,
            totalExpense = uiState.totalExpense,
            budget = uiState.budget,
            expensePercentage = uiState.expensePercentage
        ),
        modifier = modifier,
        topContent = {
            // Balance Card específico de Categories
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(13.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Honeydew
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.total_balance),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Void,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = "${uiState.balance}",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Void,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        },
        progressBarContent = { data ->
            DefaultProgressBar(
                data = data,
                description = stringResource(id = R.string.goal_description)
            )
        },
        bottomContent = {
            FinanceGridContainer {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.categories) { category ->
                        CategoryItem(
                            icon = category.iconId,
                            name = category.name,
                            onClick = {
                                when (category.name) {
                                    "Food" -> navController.navigate("food")
                                    "Transport" -> navController.navigate("transport")
                                    "Medicine" -> navController.navigate("medicine")
                                    "Groceries" -> navController.navigate("groceries")
                                    "Rent" -> navController.navigate("rent")
                                    "Gift" -> navController.navigate("gift")
                                    "Entertainment" -> navController.navigate("entertainment")
                                    "Savings" -> navController.navigate("savings")
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}
