package com.example.financeapp.ui.screen.savings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.BalanceHeader
import com.example.financeapp.ui.components.CategoryItem
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryAddSavingsButton
import com.example.financeapp.ui.screen.categories.savings.SavingsGoal
import com.example.financeapp.ui.screen.categories.savings.SavingsViewModel
import com.example.financeapp.ui.theme.*

@Composable
fun SavingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SavingsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    // Inicializar objetivos de ahorro por defecto
    LaunchedEffect(Unit) {
        if (uiState.savingsGoals.isEmpty()) {
            val defaultGoals = listOf(
                SavingsGoal(
                    title = "Car",
                    iconId = R.drawable.car,
                    goalAmount = 14390.0,
                    savedAmount = 596.25,
                    progressPercentage = 15,
                    deposits = emptyMap(),
                    route = "car_savings"
                ),
                SavingsGoal(
                    title = "New House",
                    iconId = R.drawable.newhome,
                    goalAmount = 569200.0,
                    savedAmount = 625.48,
                    progressPercentage = 30,
                    deposits = emptyMap(),
                    route = "house_savings"
                ),
                SavingsGoal(
                    title = "Travel",
                    iconId = R.drawable.travel,
                    goalAmount = 1962.93,
                    savedAmount = 653.31,
                    progressPercentage = 40,
                    deposits = emptyMap(),
                    route = "travel_savings"
                ),
                SavingsGoal(
                    title = "Wedding",
                    iconId = R.drawable.wedding_me,
                    goalAmount = 34700.0,
                    savedAmount = 296.25,
                    progressPercentage = 10,
                    deposits = emptyMap(),
                    route = "wedding_savings"
                )
            )
            defaultGoals.forEach { viewModel.addSavingsGoal(it) }
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Savings",
                showBackButton = true,
                onBackClick = { navController.navigateUp() },
                onNotificationClick = { navController.navigate("notifications") },
                containerColor = Caribbean_green
            )
        },
        bottomBar = {
            CategoryAddSavingsButton(
                onClick = { /* TODO: Navigate to add savings screen */ }
            )
        },
        containerColor = Caribbean_green
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Balance Header
            BalanceHeader(
                totalBalance = uiState.balance,
                totalExpense = uiState.totalExpense,
                budget = uiState.budget,
                progressPercentage = uiState.expensePercentage
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Grid de savings goals
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.savingsGoals) { goal ->
                        CategoryItem(
                            icon = goal.iconId,
                            name = goal.title,
                            onClick = {
                                if (goal.route.isNotEmpty()) {
                                    navController.navigate(goal.route)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
