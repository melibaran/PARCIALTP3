package com.example.financeapp.ui.screen.categories.savings.newHouse

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.screen.categories.savings.Deposit
import com.example.financeapp.ui.screen.categories.savings.SavingsViewModel
import com.example.financeapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseSavingsScreen(
    navController: NavController,
    viewModel: SavingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val existingGoal = uiState.savingsGoals.find { it.title == "New House" }

    LaunchedEffect(existingGoal) {
        existingGoal?.let { goal ->
            val houseGoalWithDeposits = goal.copy(
                deposits = mapOf(
                    "April" to listOf(
                        Deposit("House Deposit", "19:56", "April 5", 477.77)
                    ),
                    "January" to listOf(
                        Deposit("House Deposit", "20:25", "January 16", 102.67),
                        Deposit("House Deposit", "15:56", "January 02", 45.04)
                    )
                )
            )
            viewModel.updateSavingsGoal(houseGoalWithDeposits)
        }
    }

    val goal = uiState.savingsGoals.find { it.title == "New House" } ?: return

    Scaffold(
        topBar = {
            TopBar(
                title = "New House",
                showBackButton = true,
                centerTitle = true,
                onBackClick = { navController.navigateUp() },
                onNotificationClick = { navController.navigate("notifications") },
                containerColor = Caribbean_green
            )
        },
        containerColor = Caribbean_green
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp)),
                colors = CardDefaults.cardColors(containerColor = Honeydew)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    // Goal and Progress Section
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Left side: Goal and Amount Saved
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                // Goal section
                                Text(
                                    text = "Goal",
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                        fontSize = 12.sp,
                                        color = Void
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "$${String.format("%.2f", goal.goalAmount)}",
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                        fontSize = 24.sp,
                                        color = Void
                                    )
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                // Amount Saved section
                                Text(
                                    text = "Amount Saved",
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                        fontSize = 12.sp,
                                        color = Void
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "$${String.format("%.2f", goal.savedAmount)}",
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                        fontSize = 24.sp,
                                        color = Caribbean_green
                                    )
                                )
                            }

                            // Right side: Circular Progress
                            Box(
                                modifier = Modifier.size(140.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Card(
                                    modifier = Modifier.size(140.dp),
                                    colors = CardDefaults.cardColors(containerColor = Light_blue),
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            CircularProgressIndicator(
                                                progress = goal.progressPercentage / 100f,
                                                modifier = Modifier.size(100.dp),
                                                color = Color(0xFF1E88E5),
                                                strokeWidth = 8.dp,
                                                trackColor = Color.White.copy(alpha = 0.3f)
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Icon(
                                                painter = painterResource(id = goal.iconId),
                                                contentDescription = goal.title,
                                                modifier = Modifier.size(32.dp),
                                                tint = Color.White
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = goal.title,
                                                style = TextStyle(
                                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                                    fontSize = 12.sp,
                                                    color = Color.White
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Horizontal Progress Bar
                        Column {
                            // Progress bar with text inside
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(20.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Caribbean_green)
                            ) {
                                // Filled portion with percentage
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(goal.progressPercentage / 100f)
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(Fence_green)
                                ) {
                                    // Percentage text inside filled area
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(horizontal = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "${goal.progressPercentage}%",
                                            style = TextStyle(
                                                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                                fontSize = 14.sp,
                                                color = Color.White
                                            )
                                        )
                                    }
                                }
                                
                                // Total amount text always at the end
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "$${String.format("%.2f", goal.goalAmount)}",
                                        style = TextStyle(
                                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                            fontSize = 14.sp,
                                            color = Color.White
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Status message
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.check_progress),
                                    contentDescription = "Check",
                                    modifier = Modifier.size(16.dp),
                                    tint = Fence_green
                                )
                                Text(
                                    text = "30% of your expenses, looks good.",
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                        fontSize = 15.sp,
                                        color = Void
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))
                    }

                    // Transactions Section - Show all months
                    goal.deposits.forEach { (month, deposits) ->
                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = month,
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                        fontSize = 16.sp,
                                        color = Void
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        items(deposits) { deposit ->
                            DepositItem(deposit = deposit, iconRes = R.drawable.newhome)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                    // Add Savings Button
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { /* TODO: Add savings */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Caribbean_green
                            ),
                            shape = RoundedCornerShape(25.dp)
                        ) {
                            Text(
                                text = "Add Savings",
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DepositItem(deposit: Deposit, iconRes: Int = R.drawable.newhome) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp) // igual que Travel
                    .clip(CircleShape)
                    .background(Light_blue),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = "Transaction",
                    modifier = Modifier.size(28.dp), // igual que Travel
                    contentScale = androidx.compose.ui.layout.ContentScale.Fit
                )
            }
            Column {
                Text(
                    text = deposit.title,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        color = Void
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${deposit.time} - ${deposit.date}",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 12.sp,
                        color = Void.copy(alpha = 0.6f)
                    )
                )
            }
        }

        Text(
            text = "$${String.format("%.2f", deposit.amount)}",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                fontSize = 16.sp,
                color = Void
            )
        )
    }
}
