package com.example.financeapp.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Void
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    onBackClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onPasswordClick: () -> Unit = {},
    onDeleteAccountClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Caribbean_green)
    ) {
        Column {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.settings),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Void,
                            fontSize = 22.sp
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(R.drawable.bring_back),
                            contentDescription = stringResource(R.string.go_back),
                            tint = Honeydew,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNotificationClick) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Honeydew, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.bell),
                                contentDescription = stringResource(R.string.notifications) ,
                                modifier = Modifier.size(24.dp),
                                tint = Void
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Caribbean_green
                )
            )
            // Card principal
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, start = 24.dp, end = 24.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    SettingsItem(
                        iconRes = R.drawable.bell,
                        text = stringResource(R.string.notifications_settings),
                        onClick = {
                            navController.navigate("notification_settings")
                            onNotificationClick()
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SettingsItem(
                        iconRes = R.drawable.key,
                        text = stringResource(R.string.password_settings),
                        onClick = onPasswordClick
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SettingsItem(
                        iconRes = R.drawable.profile,
                        text = stringResource(R.string.delete_account),
                        onClick = onDeleteAccountClick
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsItem(
    iconRes: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Caribbean_green),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = Void,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Void,
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(R.drawable.arrow_right),
            contentDescription = null,
            tint = Void,
            modifier = Modifier.size(24.dp)
        )
    }
}

