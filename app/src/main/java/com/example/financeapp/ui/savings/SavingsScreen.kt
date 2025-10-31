package com.example.financeapp.ui.savings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.ui.savings.SavingsColors.blueIcon
import com.example.financeapp.ui.savings.SavingsColors.darkTeal
import com.example.financeapp.ui.savings.SavingsColors.lightBg
import com.example.financeapp.ui.savings.SavingsColors.tealColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavingsScreen(
    savingsGoal: SavingsGoal,
    onBackClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onCalendarClick: () -> Unit = {},
    onAddSavingsClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        savingsGoal.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(R.drawable.bring_back),
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNotificationClick) {
                        Icon(
                            painter = painterResource(R.drawable.bell),
                            contentDescription = "Notifications"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = tealColor,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                )
            )
        },
        containerColor = tealColor
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                SavingsCard(
                    savingsGoal = savingsGoal,
                    onCalendarClick = onCalendarClick,
                    onAddSavingsClick = onAddSavingsClick
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun SavingsCard(
    savingsGoal: SavingsGoal,
    onCalendarClick: () -> Unit,
    onAddSavingsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = lightBg
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            // Header: Goal Amount + Icon
            GoalHeader(savingsGoal)

            Spacer(modifier = Modifier.height(24.dp))

            // Progress Bar
            ProgressSection(savingsGoal)

            Spacer(modifier = Modifier.height(16.dp))

            // Status Message
            StatusMessage()

            Spacer(modifier = Modifier.height(24.dp))

            // Deposits by Month
            DepositsSection(
                deposits = savingsGoal.deposits,
                painter = savingsGoal.painter,
                onCalendarClick = onCalendarClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Add Savings Button
            AddSavingsButton(onClick = onAddSavingsClick)
        }
    }
}

@Composable
fun GoalHeader(savingsGoal: SavingsGoal) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.check),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Goal",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            Text(
                "$${String.format("%,.2f", savingsGoal.goalAmount)}",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.income),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Amount Saved",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            Text(
                "$${String.format("%,.2f", savingsGoal.savedAmount)}",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = tealColor
            )
        }

        // Category Icon
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(blueIcon),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = savingsGoal.painter,
                    contentDescription = savingsGoal.title,
                    modifier = Modifier.size(48.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    savingsGoal.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun ProgressSection(savingsGoal: SavingsGoal) {
    Column {
        LinearProgressIndicator(
            progress = savingsGoal.progressPercentage / 100f,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp)),
            color = tealColor,
            trackColor = darkTeal
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "${savingsGoal.progressPercentage}%",
                fontSize = 12.sp,
                color = Color.Black.copy(alpha = 0.6f)
            )
            Text(
                "$${String.format("%,.2f", savingsGoal.goalAmount)}",
                fontSize = 12.sp,
                color = tealColor
            )
        }
    }
}

@Composable
fun StatusMessage() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(R.drawable.check),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = Color.Black.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            "30% Of Your Expenses, Looks Good.",
            fontSize = 14.sp,
            color = Color.Black.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun DepositsSection(
    deposits: Map<String, List<Deposit>>,
    painter: Painter,
    onCalendarClick: () -> Unit
) {
    deposits.forEach { (month, depositList) ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                month,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            IconButton(
                onClick = onCalendarClick,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(tealColor)
            ) {
                Icon(
                    painter = painterResource(R.drawable.check),
                    contentDescription = "Calendar",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        depositList.forEach { deposit ->
            DepositItem(deposit, painter)
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun DepositItem(deposit: Deposit, painter: Painter) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(blueIcon),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painter,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    deposit.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    "${deposit.time} - ${deposit.date}",
                    fontSize = 14.sp,
                    color = Color(0xFF1976D2)
                )
            }
        }

        Text(
            "$${String.format("%.2f", deposit.amount)}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun AddSavingsButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = tealColor
        ),
        shape = RoundedCornerShape(28.dp)
    ) {
        Text(
            "Add Savings",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}
