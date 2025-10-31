package com.example.financeapp.ui.theme.screen.securitypin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.request.colorSpace
import com.example.financeapp.ui.components.AuthScreenLayout
import com.example.financeapp.ui.components.BottomDesign
import com.example.financeapp.ui.components.PrimaryButton
import com.example.financeapp.ui.components.SecondaryButton
import com.example.financeapp.ui.theme.FinanceAppTheme
import com.example.financeapp.ui.theme.Vivid_blue
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily

@Composable
fun SecurityPinScreen(
    onContinueClick: (String) -> Unit,
    onSignUpClick: () -> Unit = {}
) {
    var pin by remember { mutableStateOf("") }

    AuthScreenLayout(title = "Enter Your PIN") {
        Text(
            text = "Enter security PIN",
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Void,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

        PinInputField(
            pin = pin,
            onPinChange = { newPin ->
                if (newPin.length == 6) {
                    pin = newPin
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Accept",
            onClick = { onContinueClick(pin) },
            enabled = pin.length == 6
        )
        Spacer(modifier = Modifier.height(24.dp))
        SecondaryButton(text = "Send Again", onClick = {})

        Spacer(modifier = Modifier.height(46.dp))

        BottomDesign(
            onSignUpClick = { onSignUpClick() },
            modifier = Modifier ,
            linkColor = Vivid_blue
        )
    }
}

@Composable
fun PinInputField(
    pin: String,
    onPinChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }



    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = pin,
            onValueChange = onPinChange,
            modifier = Modifier,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.Blue,
                focusedTextColor = Color.Blue,
                unfocusedBorderColor = Color.Blue,
                focusedBorderColor = Color.Blue,
                cursorColor = Color.Blue
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (i in 0 until 6) {
                val digit = pin.getOrNull(i)?.toString() ?: ""
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .width(50.dp)
                        .height(60.dp)
                        .let {
                            if (pin.length == i) {
                                it.padding(bottom = 2.dp)
                            } else {
                                it
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = digit,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Void
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecurityPinScreenPreview() {
    FinanceAppTheme {
        SecurityPinScreen(onContinueClick = {})
    }
}

