package com.example.financeapp.ui.screen.categories.rent

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
            FinanceHeader(
                title = "Rent",
                onBackClick = { navController.navigateUp() },
                onNotificationClick = { navController.navigate("notifications") }
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
            CategoryBalanceExpenseRow(
                balance = uiState.balance,
                totalExpense = uiState.totalExpense
            )

            CategoryProgressBar(
                expensePercentage = uiState.expensePercentage,
                budget = uiState.budget
            )

            Spacer(modifier = Modifier.height(16.dp))

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

