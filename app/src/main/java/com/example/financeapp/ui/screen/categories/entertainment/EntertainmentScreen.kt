package com.example.financeapp.ui.screen.categories.entertainment

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.ui.components.BalanceHeader
import com.example.financeapp.ui.screen.categories.arquitectura.*
import com.example.financeapp.ui.theme.*

@Composable
fun EntertainmentScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: EntertainmentViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }

    val coloresDeCirculo = remember {
        listOf(Light_blue, Vivid_blue, Ocean_blue, Vivid_blue, Light_blue)
    }

    CategoryDesign(
        title = "Entertainment",
        content = {
            BalanceHeader( totalBalance = 7783.00, totalExpense = 1187.40, budget = 20000.00, progressPercentage = 30)
            CategoryTransactionList(
                transactions = uiState.transactions.map { entertainmentTx ->
                    CategoryTransactionItem(
                        id = entertainmentTx.id,
                        title = entertainmentTx.title,
                        dateTime = entertainmentTx.dateTime,
                        amount = entertainmentTx.amount,
                        iconId = entertainmentTx.iconId,
                        month = entertainmentTx.month
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


