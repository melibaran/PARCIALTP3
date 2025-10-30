package com.example.financeapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.financeapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    subtitle: String? = null,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Column {
                Text(text = title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                if (subtitle != null) {
                    Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {
            IconButton(onClick = onNotificationClick) {
                Image(painter = painterResource(id = R.drawable.bell), contentDescription = "Notifications")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun BalanceSummaryCard(
    totalBalance: Double,
    totalExpense: Double,
    progress: Float,
    goalAmount: Double,
    description: String
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val currentProgress = animateFloatAsState(
        targetValue = if (animationPlayed) progress else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "progressAnimation"
    )

    LaunchedEffect(Unit) {
        animationPlayed = true
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(R.string.total_balance), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.bodyMedium)
                    Text(String.format(java.util.Locale.US, "$%,.2f", totalBalance), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(R.string.total_expense), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.bodyMedium)
                    Text(String.format(java.util.Locale.US, "-$%,.2f", totalExpense), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(
                progress = { currentProgress.value },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp)),
                color = MaterialTheme.colorScheme.onPrimary,
                trackColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("${(progress * 100).toInt()}%", color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.bodySmall)
                Text(String.format(java.util.Locale.US, "$%,.2f", goalAmount), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.check), contentDescription = "Check")
                Spacer(modifier = Modifier.width(8.dp))
                Text(description, color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun TransactionItem(
    @DrawableRes iconResId: Int,
    title: String,
    category: String,
    date: String,
    amount: String,
    isExpense: Boolean
) {
    val amountColor = if (isExpense) Color.Red else Color(0xFF008000) // Dark Green

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Image(painter = painterResource(id = iconResId), contentDescription = title)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                    Text(date, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(category, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                if (category.isNotEmpty()) {
                    VerticalDivider(modifier = Modifier.padding(horizontal = 8.dp).height(24.dp))
                }
                Text(amount, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = amountColor)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
    )
}
