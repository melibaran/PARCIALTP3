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
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Void


private val BarBackground = Light_green
private val ActiveTurquoise = Caribbean_green
private val InactiveIcon = Color(0xFFA0A0A0)

private val navItems = listOf(
    BottomNavItem(R.drawable.home, "Home", "home", 25.dp, 31.dp),
    BottomNavItem(R.drawable.analysis, "Analytics", "analytics", 31.dp, 30.dp),
    BottomNavItem(R.drawable.transactions, "Transfer", "transfer", 33.dp, 25.dp),
    BottomNavItem(R.drawable.vector, "Layers", "layers", 27.dp, 23.dp),
    BottomNavItem(R.drawable.vector_1, "Profile", "profile", 22.dp, 27.dp)
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
        color = Light_green,
        tonalElevation = 0.dp,
        contentColor = Color.Transparent

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
                tint = if (isSelected) Void else Void,
                modifier = Modifier.size(width = item.width, height = item.height)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FinanceBottomBarPreview() {
    MaterialTheme {
        Surface(color = com.example.financeapp.ui.theme.Honeydew) {
            FinanceBottomBar()
        }
    }
}
