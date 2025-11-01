package com.example.financeapp.ui.screen.categories.arquitectura

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.financeapp.R
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Void

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceHeader(
    title: String,
    onBackClick: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Void,
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(R.drawable.bring_back),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(19.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = onNotificationClick) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.bell),
                        contentDescription = "Notifications",
                        modifier = Modifier.size(24.dp),
                        tint = Void
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Caribbean_green
        ),
        modifier = modifier
    )
}