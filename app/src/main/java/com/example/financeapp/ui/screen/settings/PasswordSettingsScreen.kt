package com.example.financeapp.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Void

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordSettingsScreen(
    navController: NavController,
    viewModel: PasswordSettingsViewModel = hiltViewModel(),
    onBackClick: () -> Unit = { navController.navigateUp() }
) {
    val uiState by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            viewModel.resetSuccess()
            navController.navigate("password_success") {
                launchSingleTop = true
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.password_settings),
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
                    IconButton(onClick = { navController.navigate("notifications") }) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Honeydew, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.bell),
                                contentDescription = stringResource(R.string.notifications),
                                modifier = Modifier.size(24.dp),
                                tint = Void
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Caribbean_green)
            )
        }
    ) { paddingValues ->
         Box(
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(paddingValues)
                 .background(Caribbean_green)
         ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, start = 24.dp, end = 24.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PasswordField(
                        label = stringResource(R.string.current_password),
                        password = uiState.currentPassword,
                        onPasswordChange = viewModel::onCurrentPasswordChange,
                        showPassword = uiState.currentPasswordVisible,
                        onShowPasswordChange = { viewModel.toggleCurrentPasswordVisibility() }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    PasswordField(
                        label = stringResource(R.string.new_password),
                        password = uiState.newPassword,
                        onPasswordChange = viewModel::onNewPasswordChange,
                        showPassword = uiState.newPasswordVisible,
                        onShowPasswordChange = { viewModel.toggleNewPasswordVisibility() }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    PasswordField(
                        label = stringResource(R.string.confirm_new_password),
                        password = uiState.confirmPassword,
                        onPasswordChange = viewModel::onConfirmPasswordChange,
                        showPassword = uiState.confirmPasswordVisible,
                        onShowPasswordChange = { viewModel.toggleConfirmPasswordVisibility() }
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Button(
                        onClick = { viewModel.changePassword() },
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(56.dp)
                            .clip(RoundedCornerShape(28.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Caribbean_green)
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
                        } else {
                            Text(
                                text = stringResource(R.string.change_password),
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    color = Void,
                                    fontSize = 18.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PasswordField(
    label: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    showPassword: Boolean,
    onShowPasswordChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Void,
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Light_green),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent),
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Void,
                        fontSize = 20.sp
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Caribbean_green,
                        unfocusedTextColor = Void,
                        focusedTextColor = Void
                    )
                )
                IconButton(onClick = { onShowPasswordChange(!showPassword) }) {
                    Icon(
                        painter = painterResource(
                            if (showPassword) R.drawable.eye_open else R.drawable.eye_pass
                        ),
                        contentDescription = if (showPassword) "Hide password" else "Show password",
                        tint = Void,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}