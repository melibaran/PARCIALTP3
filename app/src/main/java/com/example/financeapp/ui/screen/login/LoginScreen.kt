package com.example.financeapp.ui.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    onForgotPasswordClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginEnabled = email.isNotBlank() && password.isNotBlank()

    AuthScreenLayout(title = "Welcome") {

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
        PrimaryButton(text = "Log In", onClick = onLoginClick, enabled = loginEnabled)
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
