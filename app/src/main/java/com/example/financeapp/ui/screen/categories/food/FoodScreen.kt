package com.example.financeapp.ui.screen.categories.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.ui.components.BalanceHeader
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryAddExpensesButton
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryDatePicker
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryTransactionItem
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryTransactionList
import com.example.financeapp.ui.theme.Caribbean_green
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

