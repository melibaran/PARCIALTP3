package com.example.financeapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily

@Composable
fun SecurityScreen(
    onBackClick: () -> Unit = {},
    onChangePinClick: () -> Unit = {},
    onFingerprintClick: () -> Unit = {},
    onTermsClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Caribbean_green)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.bring_back),
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Text(
                    text = stringResource(R.string.security_title),
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Void,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                
                IconButton(onClick = { }) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bell),
                            contentDescription = "Notifications",
                            tint = Caribbean_green,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(30.dp))
            
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
                colors = CardDefaults.cardColors(containerColor = com.example.financeapp.ui.theme.Honeydew)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.security_title),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Void
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        SecurityMenuItem(
            text = stringResource(R.string.change_pin),
            onClick = onChangePinClick
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        SecurityMenuItem(
            text = stringResource(R.string.fingerprint),
            onClick = onFingerprintClick
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        SecurityMenuItem(
            text = stringResource(R.string.terms_and_conditions),
            onClick = onTermsClick
        )
        
        Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun SecurityMenuItem(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Void
        )
        
        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = "Navigate",
            tint = Void,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview(
    showBackground = true,
    name = "Security Screen",
    showSystemUi = true
)
@Composable
fun SecurityScreenPreview() {
    MaterialTheme {
        SecurityScreen()
    }
}

