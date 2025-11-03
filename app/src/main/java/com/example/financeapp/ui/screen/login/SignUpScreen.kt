package com.example.financeapp.ui.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.ui.components.AuthScreenLayout
import com.example.financeapp.ui.components.AuthTextField
import com.example.financeapp.ui.components.BottomAuthText
import com.example.financeapp.ui.components.PasswordTextField
import com.example.financeapp.ui.components.PrimaryButton
import com.example.financeapp.ui.theme.Vivid_blue
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    viewModel: SignUpViewModel = hiltViewModel()
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    
    val signUpState by viewModel.signUpState.collectAsState()
    
    LaunchedEffect(signUpState) {
        when (val state = signUpState) {
            is SignUpState.Success -> {
                onSignUpClick()
                viewModel.resetState()
            }
            is SignUpState.Error -> {
                errorMessage = state.message
            }
            else -> {
                errorMessage = null
            }
        }
    }

    AuthScreenLayout(title = "Create Account") {
        
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
            value = fullName,
            onValueChange = { fullName = it },
            label = "Full Name",
            placeholder = "Juan Pérez"
        )
        Spacer(Modifier.height(16.dp))
        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            placeholder = "example@example.com"
        )
        Spacer(Modifier.height(16.dp))
        AuthTextField(
            value = mobileNumber,
            onValueChange = { mobileNumber = it },
            label = "Mobile Number",
            placeholder = "+ 123 456 789",
            keyboardType = KeyboardType.Phone
        )
        Spacer(Modifier.height(16.dp))
        AuthTextField(
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = "Date Of Birth",
            placeholder = "DD / MM / YYYY"
        )
        Spacer(Modifier.height(16.dp))
        PasswordTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "**********",

        )
        Spacer(Modifier.height(16.dp))
        PasswordTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password",
            placeholder = "**********"
        )

        Spacer(Modifier.height(24.dp))

        Text(
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            color = Void,
            fontSize = 14.sp,
            text = buildAnnotatedString {
                append("By continuing, you agree to\n")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Terms of Use")
                }
                append(" and ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Privacy Policy")
                }
                append(".")
            },
            modifier = Modifier.padding(horizontal = 16.dp).align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(24.dp))

        if (signUpState is SignUpState.Loading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            PrimaryButton(
                text = "Sign Up", 
                onClick = { 
                    errorMessage = null
                    viewModel.signUp(
                        fullName = fullName,
                        email = email,
                        password = password,
                        confirmPassword = confirmPassword
                    )
                }, 
                enabled = fullName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()
            )
        }

        Spacer(Modifier.height(16.dp))

        BottomAuthText(
            text = "Already have an account? ",
            linkText = "Log In",
            onLinkClick = onLoginClick,
            modifier = Modifier,
            linkColor = Vivid_blue
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}
