package com.example.financeapp.ui.screen.categories.arquitectura

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.theme.*

@Composable
fun CategoryDesign(title: String,content:@Composable ColumnScope.() -> Unit,navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(
                title = title,
                showBackButton = true,
                centerTitle = true,
                onBackClick = { navController.navigateUp() },
                onNotificationClick = { navController.navigate("notifications") },
                containerColor = Caribbean_green
            )
        },
        containerColor = Caribbean_green
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
        {
            content()
        }
    }
}

@Composable
fun CategoryAddExpensesButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Honeydew)
            .padding(bottom = 80.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .padding(horizontal = 95.dp)
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Caribbean_green
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Add Expenses",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    color = Void,
                    fontSize = 16.sp,
                )
            )
        }
    }
}

//AddSavingsBottom
@Composable
fun CategoryAddSavingsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Honeydew)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .width(169.dp)
                .height(36.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Caribbean_green
            ),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text(
                text = "Add Savings",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                    color = Honeydew,
                    fontSize = 16.sp,
                )
            )
        }
    }
}


