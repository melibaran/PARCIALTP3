package com.example.financeapp.ui.screen.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.components.AuthScreenLayout
import com.example.financeapp.ui.components.PasswordTextField
import com.example.financeapp.ui.components.PrimaryButton

@Composable
fun NewPasswordScreen(
    onChangeClick: () -> Unit = {},
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    AuthScreenLayout(title = "New Password") {
        PasswordTextField(
            value = password,
            onValueChange = { password = it },
            label = "New Password",
            placeholder = "**********",

            )
        Spacer(Modifier.height(16.dp))
        PasswordTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm New Password",
            placeholder = "**********"
        )
        Spacer(Modifier.height(86.dp))
        PrimaryButton("Change Password", onClick = onChangeClick, enabled = password.isNotBlank() && confirmPassword.isNotBlank() && password == confirmPassword)
    }

}