package com.example.financeapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.Dp
import com.example.financeapp.R
import com.example.financeapp.ui.theme.Ocean_blue
import com.example.financeapp.ui.theme.Void
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Brush
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.theme.Vivid_blue

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
                Text(text = title, style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground), fontWeight = FontWeight.Bold)
                if (subtitle != null) {
                    Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f))
                }
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Image(painter = painterResource(id = R.drawable.bring_back), contentDescription = "Back")
                }
            }
        },
        actions = {
            IconButton(onClick = onNotificationClick) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.bell), contentDescription = "Notifications", modifier = Modifier.padding(6.dp))
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(id = R.drawable.arrow_up), contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(stringResource(R.string.total_balance), color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.bodyMedium)
                    }
                    Text(String.format(java.util.Locale.US, "$%,.2f", totalBalance), color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(id = R.drawable.arrow_down), contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(stringResource(R.string.total_expense), color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.bodyMedium)
                    }
                    Text(String.format(java.util.Locale.US, "-$%,.2f", totalExpense), color = Ocean_blue, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(
                progress = { currentProgress.value },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(10.dp)),
                color = MaterialTheme.colorScheme.onBackground,
                trackColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("${(progress * 100).toInt()}%", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.bodySmall)
                Text(String.format(java.util.Locale.US, "$%,.2f", goalAmount), color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.check), contentDescription = "Check")
                Spacer(modifier = Modifier.width(8.dp))
                Text(description, color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.bodySmall)
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
    val amountColor = if (isExpense) Ocean_blue else MaterialTheme.colorScheme.primary

    // Selección de color para el fondo circular del icono según el icono
    val iconBgColor = when (iconResId) {
        R.drawable.salary -> Light_blue
        R.drawable.food -> Light_blue
        R.drawable.groceries -> Vivid_blue
        R.drawable.rent -> Ocean_blue
        else -> MaterialTheme.colorScheme.secondaryContainer
    }

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
                        .background(iconBgColor),
                    contentAlignment = Alignment.Center
                ) {
                    Image(painter = painterResource(id = iconResId), contentDescription = title, modifier = Modifier.size(24.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                    Text(date, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(category, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                if (category.isNotEmpty()) {
                    VerticalDivider(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                    )
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

@Composable
fun VerticalDivider(modifier: Modifier = Modifier, thickness: Dp = 1.dp, color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)) {
    Box(modifier = modifier
        .width(thickness)
        .background(color)
    )
}
