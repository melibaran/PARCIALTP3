package com.example.financeapp.ui.screen.categories.transport

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.ui.components.BalanceHeader
import com.example.financeapp.ui.screen.categories.arquitectura.*
import com.example.financeapp.ui.theme.*

@Composable
fun TransportScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: TransportViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }

    val coloresDeCirculo = remember {
        listOf(Light_blue, Vivid_blue, Ocean_blue, Vivid_blue, Light_blue)
    }

    CategoryDesign(
        title = "Transport",
        content = {
            BalanceHeader( totalBalance = 7783.00, totalExpense = 1187.40, budget = 20000.00, progressPercentage = 30)

            CategoryTransactionList(
                transactions = uiState.transactions.map { transportTx ->
                    CategoryTransactionItem(
                        id = transportTx.id,
                        title = transportTx.title,
                        dateTime = transportTx.dateTime,
                        amount = transportTx.amount,
                        iconId = transportTx.iconId,
                        month = transportTx.month
                    )
                },
                availableMonths = viewModel.getAvailableMonths(),
                onDatePickerClick = { showDatePicker = true },
                modifier = Modifier.weight(1f),
                circleColors = coloresDeCirculo
            )

            CategoryAddExpensesButton(
                onClick = { navController.navigate("add_expenses") }
            )
        },
        navController = navController
    )

    CategoryDatePicker(
        showDatePicker = showDatePicker,
        onDismiss = { showDatePicker = false }
    )
}

