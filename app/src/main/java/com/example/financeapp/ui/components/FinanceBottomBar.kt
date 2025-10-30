package com.example.financeapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financeapp.R
import com.example.financeapp.ui.navigation.BottomNavItem
import com.example.financeapp.ui.navigation.BottomNavViewModel

private val BarBackground = Color(0xFFE8F7EF)
private val ActiveTurquoise = Color(0xFF05C3A7)
private val InactiveIcon = Color(0xFF0A0A0A)

private val navItems = listOf(
    BottomNavItem(R.drawable.ic_home, "Home", "home"),
    BottomNavItem(R.drawable.ic_analytics, "Analytics", "analytics"),
    BottomNavItem(R.drawable.ic_transfer, "Transfer", "transfer"),
    BottomNavItem(R.drawable.ic_layers, "Layers", "layers"),
    BottomNavItem(R.drawable.ic_profile, "Profile", "profile")
)

@Composable
fun FinanceBottomBar(
    modifier: Modifier = Modifier,
    viewModel: BottomNavViewModel = viewModel(),
    onNavigate: (String) -> Unit = {}
) {
    val selectedRoute by viewModel.selectedRoute.collectAsState()

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        color = BarBackground,
        tonalElevation = 0.dp,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEach { item ->
                BottomNavItem(
                    item = item,
                    isSelected = selectedRoute == item.route,
                    onItemClick = {
                        viewModel.onNavItemSelected(item.route)
                        onNavigate(item.route)
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(if (isSelected) CircleShape else RoundedCornerShape(0.dp))
                .background(if (isSelected) ActiveTurquoise else Color.Transparent)
                .clickable(onClick = onItemClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.label,
                tint = if (isSelected) Color.White else InactiveIcon,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FinanceBottomBarPreview() {
    MaterialTheme {
        FinanceBottomBar()
    }
}
