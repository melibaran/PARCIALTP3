package com.example.financeapp.ui.screen.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.ui.components.AuthScreenLayout
import com.example.financeapp.ui.components.AuthTextField
import com.example.financeapp.ui.components.BottomDesign
import com.example.financeapp.ui.components.PrimaryButton
import com.example.financeapp.ui.components.SecondaryButton
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.Vivid_blue
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily


@Composable
fun ForgotPasswordScreen(
    onNextStepClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }

    AuthScreenLayout(title = "Forgot Password") {

        Text("Reset Password?",
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Void,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.Start)
        )
        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 8.sp,
            color = Void,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 8.dp, bottom = 24.dp)
        )
        Spacer(Modifier.height(30.dp))

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            label = "Enter Email Address",
            placeholder = "example@example.com"
        )
        Spacer(Modifier.height(26.dp))
        PrimaryButton(text = "Next Step", onClick = onNextStepClick, enabled = email.isNotBlank())
        Spacer(Modifier.height(50.dp))
        SecondaryButton("Sign Up", onClick = onSignUpClick)

        BottomDesign(
            onSignUpClick = { onSignUpClick() },
            modifier = Modifier ,
            linkColor = Vivid_blue
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    FinanceAppTheme {
        ForgotPasswordScreen(onNextStepClick = {}, onSignUpClick = {})
    }
}
