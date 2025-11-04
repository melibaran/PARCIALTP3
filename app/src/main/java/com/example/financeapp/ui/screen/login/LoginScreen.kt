package com.example.financeapp.ui.screen.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.ui.components.AuthScreenLayout
import com.example.financeapp.ui.components.AuthTextField
import com.example.financeapp.ui.components.PasswordTextField
import com.example.financeapp.ui.components.PrimaryButton
import com.example.financeapp.ui.components.SecondaryButton
import com.example.financeapp.ui.theme.Ocean_blue
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily


@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    viewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginEnabled = email.isNotBlank() && password.isNotBlank()
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        when (val state = loginState) {
            is LoginState.Success -> {
                onLoginClick()
                viewModel.resetState()
            }
            is LoginState.Error -> {
                errorMessage = state.message
            }
            else -> {
                errorMessage = null
            }
        }
    }

    AuthScreenLayout(title = "Welcome") {

        errorMessage?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "Username Or Email",
            placeholder = "example@example.com",
            keyboardType = KeyboardType.Email
        )

        Spacer(Modifier.height(16.dp))

        PasswordTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "**********"
        )

        Spacer(Modifier.height(36.dp))

        if (loginState is LoginState.Loading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            PrimaryButton(
                text = "Log In",
                onClick = {
                    errorMessage = null
                    viewModel.login(email, password)
                },
                enabled = loginEnabled
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            TextButton(onClick = onForgotPasswordClick) {
                Text("Forgot Password?", color = Void, fontFamily = poppinsFamily)
            }
        }


        SecondaryButton(text = "Sign Up", onClick = onSignUpClick)
        Spacer(Modifier.height(16.dp))

        TextButton(onClick = { /* TODO: Fingerprint */ }) {
            val annotatedText = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Void, fontFamily = poppinsFamily, fontWeight = FontWeight.SemiBold)) { append("Use ") }
                withStyle(style = SpanStyle(color = Ocean_blue, fontFamily = poppinsFamily, fontWeight = FontWeight.SemiBold)) { append("Fingerprint") }
                withStyle(style = SpanStyle(color = Void, fontFamily = poppinsFamily, fontWeight = FontWeight.SemiBold)) { append(" To Access") }
            }
            Text(annotatedText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
