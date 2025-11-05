package com.example.financeapp.ui.screen.categories.food

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.ui.components.BalanceHeader
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryAddExpensesButton
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryDatePicker
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryDesign
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryTransactionItem
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryTransactionList
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.theme.Ocean_blue
import com.example.financeapp.ui.theme.Vivid_blue

@Composable
fun FoodScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FoodViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }

    val coloresDeCirculo = remember {
        listOf(Light_blue, Vivid_blue, Ocean_blue, Vivid_blue, Light_blue)
    }

    CategoryDesign(
        title = "Food",
        content = {
            BalanceHeader( totalBalance = 7783.00, totalExpense = 1187.40, budget = 20000.00, progressPercentage = 30)
            CategoryTransactionList(
                transactions = uiState.transactions.map { foodTx ->
                    CategoryTransactionItem(
                        id = foodTx.id,
                        title = foodTx.title,
                        dateTime = foodTx.dateTime,
                        amount = foodTx.amount,
                        iconId = foodTx.iconId,
                        month = foodTx.month
                    )
                },
                availableMonths = viewModel.getAvailableMonths(),
                onDatePickerClick = { showDatePicker = true },
                modifier = Modifier.weight(1f),
                circleColors = coloresDeCirculo
            )
            CategoryAddExpensesButton(
                onClick = { navController.navigate("add_expenses") },
                tittle = "Add Expenses"
            )
        },
        navController = navController
    )

    CategoryDatePicker(
        showDatePicker = showDatePicker,
        onDismiss = { showDatePicker = false }
    )
}