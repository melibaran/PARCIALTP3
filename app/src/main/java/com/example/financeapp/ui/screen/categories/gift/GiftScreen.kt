package com.example.financeapp.ui.screen.categories.gift

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

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopBar(
            title = "Gift",
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
            onClick = { navController.navigate("add_expenses") }
        )
    }

    CategoryDatePicker(
        showDatePicker = showDatePicker,
        onDismiss = { showDatePicker = false }
    )
}

