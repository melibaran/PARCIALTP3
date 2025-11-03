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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.lerp
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Fence_green
import com.example.financeapp.ui.theme.Light_green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    subtitle: String? = null,
    showBackButton: Boolean = false,
    centerTitle: Boolean = false,
    onBackClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.background
) {
    TopAppBar(
        title = {
            Box(
                modifier = if (centerTitle) Modifier.fillMaxWidth() else Modifier,
                contentAlignment = if (centerTitle) Alignment.Center else Alignment.CenterStart
            ) {
                Column(
                    horizontalAlignment = if (centerTitle) Alignment.CenterHorizontally else Alignment.Start
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground),
                        fontWeight = FontWeight.Bold
                    )
                    if (subtitle != null) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                        )
                    }
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
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bell),
                        contentDescription = "Notifications",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Composable
fun BalanceHeader(
    totalBalance: Double,
    totalExpense: Double,
    budget: Double,
    progressPercentage: Int
) {
    val formatter = java.text.NumberFormat.getNumberInstance(java.util.Locale.US).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Total Balance y Total Expense
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Columna de Balance
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Image(painter = painterResource(id = R.drawable.arrow_up), contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.total_balance),
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
                Text(
                    text = "$${formatter.format(totalBalance)}",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, color = Color.White)
                )
            }

            // Divisor Vertical
            VerticalDivider(
                modifier = Modifier.height(48.dp).padding(horizontal = 14.dp),
                thickness = 1.dp,
                color = Light_green
            )

            // Columna de Gastos
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Image(painter = painterResource(id = R.drawable.arrow_down), contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.total_expense),
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
                Text(
                    text = "-$${formatter.format(totalExpense)}",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold, color = Ocean_blue)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Barra de Progreso
        Column(modifier = Modifier.padding(horizontal = 21.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Honeydew)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth(progressPercentage / 100f)
                        .background(Fence_green)
                )
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$progressPercentage%",
                        modifier = Modifier.padding(start = 8.dp),
                        color = Honeydew,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "$${formatter.format(budget)}",
                        modifier = Modifier.padding(end = 8.dp),
                        style = MaterialTheme.typography.labelSmall.copy(color = Fence_green)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción de la meta
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.check), contentDescription = "Check", modifier = Modifier.size(13.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.goal_description),
                    style = MaterialTheme.typography.bodySmall.copy(color = Fence_green)
                )
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

    val iconBgColor = when (iconResId) {
        R.drawable.salary -> Light_blue
        R.drawable.food_default -> Light_blue
        R.drawable.groceries_default -> Vivid_blue
        R.drawable.rent_default -> Ocean_blue
        R.drawable.transport_default -> Light_blue
        R.drawable.medicine_default -> Vivid_blue
        R.drawable.gift_default -> Ocean_blue
        R.drawable.entertaiment_default -> Light_blue
        R.drawable.saving_default -> Vivid_blue
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
                        modifier = Modifier.padding(horizontal = 8.dp).height(32.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
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
fun VerticalDivider(modifier: Modifier = Modifier, thickness: Dp = 1.dp, color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)) {
    Box(
        modifier
            .width(thickness)
            .background(color = color)
    )
}
