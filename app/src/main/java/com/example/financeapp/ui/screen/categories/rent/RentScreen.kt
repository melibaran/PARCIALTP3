package com.example.financeapp.ui.screen.categories.rent

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.ui.components.BalanceHeader
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.screen.categories.arquitectura.*
import com.example.financeapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RentViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }

    val coloresDeCirculo = remember {
        listOf(Light_blue, Vivid_blue, Ocean_blue, Vivid_blue, Light_blue)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Rent",
                showBackButton = true,
                onBackClick = { navController.navigateUp() },
                onNotificationClick = { navController.navigate("notifications") },
                containerColor = Caribbean_green
            )
        },
        bottomBar = {
            CategoryAddExpensesButton(
                onClick = { navController.navigate("add_expenses") }
            )
        },
        containerColor = Caribbean_green
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            BalanceHeader(
                totalBalance = uiState.balance,
                totalExpense = uiState.totalExpense,
                budget = uiState.budget,
                progressPercentage = uiState.expensePercentage
            )

            CategoryTransactionList(
                transactions = uiState.transactions.map { rentTx ->
                    CategoryTransactionItem(
                        id = rentTx.id,
                        title = rentTx.title,
                        dateTime = rentTx.dateTime,
                        amount = rentTx.amount,
                        iconId = rentTx.iconId,
                        month = rentTx.month
                    )
                },
                availableMonths = viewModel.getAvailableMonths(),
                onDatePickerClick = { showDatePicker = true },
                modifier = Modifier.weight(1f),
                circleColors = coloresDeCirculo
            )
        }
    }

    CategoryDatePicker(
        showDatePicker = showDatePicker,
        onDismiss = { showDatePicker = false }
    )
}

