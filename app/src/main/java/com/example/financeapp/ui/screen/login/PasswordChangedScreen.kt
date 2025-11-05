package com.example.financeapp.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.poppinsFamily

@Composable
fun PasswordChangedScreen(
    modifier: Modifier = Modifier,
    message: String = "Password Has Been\nChanged Successfully"
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Caribbean_green),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Image(
                painter =  painterResource(id = R.drawable.check_progress),
                contentDescription = "Password Changed",
                modifier = Modifier.size(120.dp)
            )


            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = message,
                color = Honeydew,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordChangedScreenPreview() {
    FinanceAppTheme {
        PasswordChangedScreen()
    }
}
