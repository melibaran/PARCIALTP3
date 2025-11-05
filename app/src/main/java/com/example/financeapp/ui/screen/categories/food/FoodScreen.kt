package com.example.financeapp.ui.screen.categories.food

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.BalanceHeader
import com.example.financeapp.ui.components.PrimaryButton
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