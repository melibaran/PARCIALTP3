package com.example.financeapp.ui.screen.categories.gift

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.ui.components.BalanceHeader
import com.example.financeapp.ui.screen.categories.arquitectura.*
import com.example.financeapp.ui.theme.*

@Composable
fun GiftScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: GiftViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }

    val coloresDeCirculo = remember {
        listOf(Light_blue, Vivid_blue, Ocean_blue, Vivid_blue, Light_blue)
    }

    CategoryDesign(
        title = "Gift",
        content = {
            BalanceHeader( totalBalance = 7783.00, totalExpense = 1187.40, budget = 20000.00, progressPercentage = 30)
            CategoryTransactionList(
                transactions = uiState.transactions.map { giftTx ->
                    CategoryTransactionItem(
                        id = giftTx.id,
                        title = giftTx.title,
                        dateTime = giftTx.dateTime,
                        amount = giftTx.amount,
                        iconId = giftTx.iconId,
                        month = giftTx.month
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
