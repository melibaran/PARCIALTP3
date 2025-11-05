package com.example.financeapp.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Void
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSettingsScreen1(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Caribbean_green)
    ) {
        Column {
            TopBar(
                title = stringResource(R.string.notifications_settings),
                showBackButton = true,
                centerTitle = true,
                onBackClick = { navController.navigateUp() },
                onNotificationClick = { navController.navigate("notifications") },
                containerColor = Caribbean_green
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp),
                color = Honeydew
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp, start = 24.dp, end = 24.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    val switches = remember { mutableStateListOf(true, true, true, true, false, false, false, false) }
                    val labels = listOf(
                        stringResource(R.string.general_notification),
                        stringResource(R.string.sound),
                        stringResource(R.string.sound_call),
                        stringResource(R.string.vibrate),
                        stringResource(R.string.transactions),
                        stringResource(R.string.expense_reminder),
                        stringResource(R.string.budget_otifications),
                        stringResource(R.string.low_balance_alerts)
                    )
                    labels.forEachIndexed { i, label ->
                        NotificationSwitchItem(
                            text = label,
                            checked = switches[i],
                            onCheckedChange = { switches[i] = it }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationSwitchItem(
    text: String,
    checked: Boolean = true,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Void,
                fontSize = 16.sp
            ),
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Caribbean_green,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Caribbean_green.copy(alpha = 0.4f)
            )
        )
    }
}
