package com.example.financeapp.ui.screen.notification

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Ocean_blue

data class NotificationItem(
    val id: String,
    @DrawableRes val iconResId: Int,
    val title: String,
    val description: String,
    val time: String,
    val transactionDetail: String? = null
)

data class NotificationGroup(
    val groupTitle: String,
    val notifications: List<NotificationItem>
)

@Composable
fun NotificationScreen(navController: NavController) {
    val notificationGroups = listOf(
        NotificationGroup(
            groupTitle = "Today",
            notifications = listOf(
                NotificationItem(
                    id = "1",
                    iconResId = R.drawable.bell,
                    title = "Reminder!",
                    description = "Set up your automatic savings to meet your savings goal...",
                    time = "17:00 - April 24"
                ),
                NotificationItem(
                    id = "2",
                    iconResId = R.drawable.star,
                    title = "New Update",
                    description = "Set up your automatic savings to meet your savings goal...",
                    time = "17:00 - April 24"
                )
            )
        ),
        NotificationGroup(
            groupTitle = "Yesterday",
            notifications = listOf(
                NotificationItem(
                    id = "3",
                    iconResId = R.drawable.dollar,
                    title = "Transactions",
                    description = "A new transaction has been registered",
                    time = "17:00 - April 24",
                    transactionDetail = "Groceries | Pantry | -$100,00"
                ),
                NotificationItem(
                    id = "4",
                    iconResId = R.drawable.bell,
                    title = "Reminder!",
                    description = "Set up your automatic savings to meet your savings goal...",
                    time = "17:00 - April 24"
                )
            )
        ),
        NotificationGroup(
            groupTitle = "This Weekend",
            notifications = listOf(
                NotificationItem(
                    id = "5",
                    iconResId = R.drawable.arrow_down,
                    title = "Expense Record",
                    description = "We recommend that you be more attentive to your finances.",
                    time = "17:00 - April 24"
                ),
                NotificationItem(
                    id = "6",
                    iconResId = R.drawable.dollar,
                    title = "Transactions",
                    description = "A new transaction has been registered",
                    time = "",
                    transactionDetail = "Food | Dinner | -$70,40"
                )
            )
        )
    )

    Scaffold(
        topBar = {
            TopBar(
                title = "Notification",
                showBackButton = true,
                centerTitle = true,
                onBackClick = { navController.popBackStack() },
                onNotificationClick = { /* No-op */ },
                containerColor = Caribbean_green)
        },
        containerColor = Caribbean_green
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
                    .padding(16.dp)
            ) {
                notificationGroups.forEach { group ->
                    // Group header
                    item {
                        Text(
                            text = group.groupTitle,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }

                    // Notifications in this group
                    items(group.notifications) { notification ->
                        NotificationItemCard(notification = notification)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
private fun NotificationItemCard(notification: NotificationItem) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Icon circular con fondo Caribbean_green
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Caribbean_green),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = notification.iconResId),
                    contentDescription = notification.title,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Transaction detail si existe
                notification.transactionDetail?.let { detail ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = detail,
                        style = MaterialTheme.typography.bodySmall,
                        color = Ocean_blue,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // Time
                if (notification.time.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = notification.time,
                        style = MaterialTheme.typography.labelSmall,
                        color = Ocean_blue
                    )
                }
            }
        }

        // Divider verde que ocupa todo el ancho
        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = Caribbean_green.copy(alpha = 0.3f),
            thickness = 1.dp
        )
    }
}


