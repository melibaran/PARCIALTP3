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
                    Image(
                        painter = painterResource(id = R.drawable.bring_back),
                        contentDescription = "Back",
                        modifier = Modifier.size(19.dp)
                    )
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
                    Image(
                        painter = painterResource(id = R.drawable.bell),
                        contentDescription = "Notifications",
                        modifier = Modifier.size(20.dp)
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

// BalanceHeader fue movida a su propio archivo BalanceHeader.kt
// Si necesitas otra variante, usá BalanceHeader(...) desde ese archivo.

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
