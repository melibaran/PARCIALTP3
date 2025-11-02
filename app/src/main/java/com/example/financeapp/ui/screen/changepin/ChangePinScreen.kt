package com.example.financeapp.ui.screen.changepin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.R
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily

@Composable
fun ChangePinScreen(
    onBackClick: () -> Unit = {},
    onPinChanged: () -> Unit = {},
    viewModel: ChangePinViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onPinChanged()
            viewModel.resetSuccessState()
        }
    }

    if (uiState.errorMessage != null) {
        AlertDialog(
            onDismissRequest = { viewModel.clearError() },
            title = { Text("Error") },
            text = { Text(uiState.errorMessage ?: "") },
            confirmButton = {
                TextButton(onClick = { viewModel.clearError() }) {
                    Text("OK")
                }
            }
        )
    }

    ChangePinContent(
        uiState = uiState,
        onBackClick = onBackClick,
        onCurrentPinChange = viewModel::onCurrentPinChange,
        onNewPinChange = viewModel::onNewPinChange,
        onConfirmPinChange = viewModel::onConfirmPinChange,
        onCurrentPinVisibilityToggle = viewModel::toggleCurrentPinVisibility,
        onNewPinVisibilityToggle = viewModel::toggleNewPinVisibility,
        onConfirmPinVisibilityToggle = viewModel::toggleConfirmPinVisibility,
        onChangePinClick = viewModel::changePin
    )
}

@Composable
private fun ChangePinContent(
    uiState: ChangePinUiState,
    onBackClick: () -> Unit,
    onCurrentPinChange: (String) -> Unit,
    onNewPinChange: (String) -> Unit,
    onConfirmPinChange: (String) -> Unit,
    onCurrentPinVisibilityToggle: () -> Unit,
    onNewPinVisibilityToggle: () -> Unit,
    onConfirmPinVisibilityToggle: () -> Unit,
    onChangePinClick: () -> Unit
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
                    text = stringResource(R.string.change_pin),
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(20.dp))
                    
                    PinInputField(
                        label = stringResource(R.string.current_pin_label),
                        value = uiState.currentPin,
                        onValueChange = onCurrentPinChange,
                        isVisible = uiState.currentPinVisible,
                        onVisibilityToggle = onCurrentPinVisibilityToggle,
                        enabled = !uiState.isLoading
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    PinInputField(
                        label = stringResource(R.string.new_pin_label),
                        value = uiState.newPin,
                        onValueChange = onNewPinChange,
                        isVisible = uiState.newPinVisible,
                        onVisibilityToggle = onNewPinVisibilityToggle,
                        enabled = !uiState.isLoading
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    PinInputField(
                        label = stringResource(R.string.confirm_pin_label),
                        value = uiState.confirmPin,
                        onValueChange = onConfirmPinChange,
                        isVisible = uiState.confirmPinVisible,
                        onVisibilityToggle = onConfirmPinVisibilityToggle,
                        enabled = !uiState.isLoading
                    )
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    Button(
                        onClick = onChangePinClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Caribbean_green
                        ),
                        enabled = !uiState.isLoading
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                color = Void,
                                modifier = Modifier.size(24.dp)
                            )
                        } else {
                            Text(
                                text = stringResource(R.string.change_pin),
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = Void
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun PinInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isVisible: Boolean,
    onVisibilityToggle: () -> Unit,
    enabled: Boolean = true
) {
    Text(
        text = label,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = Void,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Light_green,
            focusedContainerColor = Light_green,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Caribbean_green,
            disabledContainerColor = Light_green.copy(alpha = 0.5f),
            disabledBorderColor = Color.Transparent
        ),
        enabled = enabled,
        trailingIcon = {
            IconButton(onClick = onVisibilityToggle) {
                Icon(
                    painter = painterResource(
                        id = if (isVisible) R.drawable.ojoabierto else R.drawable.ojocerrado
                    ),
                    contentDescription = "Toggle visibility",
                    tint = Void
                )
            }
        }
    )
}

@Preview(
    showBackground = true,
    name = "Change Pin Screen",
    showSystemUi = true
)
@Composable
fun ChangePinScreenPreview() {
    MaterialTheme {
        ChangePinContent(
            uiState = ChangePinUiState(),
            onBackClick = {},
            onCurrentPinChange = {},
            onNewPinChange = {},
            onConfirmPinChange = {},
            onCurrentPinVisibilityToggle = {},
            onNewPinVisibilityToggle = {},
            onConfirmPinVisibilityToggle = {},
            onChangePinClick = {}
        )
    }
}

