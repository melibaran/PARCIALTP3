package com.example.financeapp.ui.screen.categories.food

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.ui.components.BalanceHeader
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.screen.categories.arquitectura.*
import com.example.financeapp.ui.theme.*

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

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopBar(
            title = "Food",
            showBackButton = true,
            centerTitle = true,
            onBackClick = { navController.navigateUp() },
            onNotificationClick = { navController.navigate("notifications") },
            containerColor = Caribbean_green
        )

        Spacer(modifier = Modifier.height(8.dp))

        BalanceHeader(
            totalBalance = uiState.balance,
            totalExpense = uiState.totalExpense,
            budget = uiState.budget,
            progressPercentage = uiState.expensePercentage
        )

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

        // Botón Add Expenses al final
        CategoryAddExpensesButton(
            onClick = { navController.navigate("add_expenses") }
        )
    }

    CategoryDatePicker(
        showDatePicker = showDatePicker,
        onDismiss = { showDatePicker = false }
    )
}

