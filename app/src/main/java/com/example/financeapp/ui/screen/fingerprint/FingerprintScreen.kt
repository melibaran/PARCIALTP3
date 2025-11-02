package com.example.financeapp.ui.screen.fingerprint

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.R
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Vivid_blue
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily

@Composable
fun FingerprintScreen(
    onBackClick: () -> Unit,
    onFingerprintClick: (FingerprintItem) -> Unit,
    onAddFingerprintClick: () -> Unit,
    viewModel: FingerprintViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    FingerprintScreenContent(
        uiState = uiState,
        onBackClick = onBackClick,
        onFingerprintClick = onFingerprintClick,
        onAddFingerprintClick = onAddFingerprintClick
    )
}

@Composable
fun FingerprintScreenContent(
    uiState: FingerprintUiState,
    onBackClick: () -> Unit,
    onFingerprintClick: (FingerprintItem) -> Unit,
    onAddFingerprintClick: () -> Unit
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
                    text = stringResource(R.string.fingerprint_title),
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
                            contentDescription = "Notification",
                            tint = Void,
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
                colors = CardDefaults.cardColors(containerColor = Honeydew)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState())
                ) {


                    uiState.fingerprints.forEach { fingerprint ->
                        FingerprintItem(
                            name = fingerprint.name,
                            onClick = { onFingerprintClick(fingerprint) }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    AddFingerprintItem(
                        onClick = onAddFingerprintClick
                    )
                }
            }
        }
    }
}

@Composable
fun FingerprintItem(
    name: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Vivid_blue, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.fingerprint),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Honeydew
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = name,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = Void,
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Void
        )
    }
}

@Composable
fun AddFingerprintItem(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Vivid_blue, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus_finger),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Honeydew
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = stringResource(R.string.add_fingerprint),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = Void,
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Void
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FingerprintScreenPreview() {
    FingerprintScreenContent(
        uiState = FingerprintUiState(
            fingerprints = listOf(
                FingerprintItem(id = "1", name = "John Fingerprint")
            )
        ),
        onBackClick = {},
        onFingerprintClick = {},
        onAddFingerprintClick = {}
    )
}

