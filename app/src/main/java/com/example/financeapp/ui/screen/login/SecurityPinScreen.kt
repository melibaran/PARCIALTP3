package com.example.financeapp.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
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
    onContinueClick: () -> Unit,
    onSignUpClick: () -> Unit = {}
) {
    var pin by remember { mutableStateOf("") }

    AuthScreenLayout(title = "Security Pin") {
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
                // permitir hasta 6 dígitos numéricos
                val sanitized = newPin.filter { it.isDigit() }
                if (sanitized.length <= 6) {
                    pin = sanitized
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Accept",
            onClick = { onContinueClick() },
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
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(200)
        try {
            focusRequester.requestFocus()
            keyboardController?.show()
        } catch (_: Exception) {
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = pin,
            onValueChange = {
                val sanitized = it.filter { ch -> ch.isDigit() }
                if (sanitized.length <= 6) onPinChange(sanitized)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .width(1.dp)
                .height(1.dp)
                .focusRequester(focusRequester),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.size(0.dp)) {
                    innerTextField()
                }
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    try {
                        focusRequester.requestFocus()
                        keyboardController?.show()
                    } catch (_: Exception) {
                    }
                },
            horizontalArrangement = Arrangement.Center
        ) {
            for (i in 0 until 6) {
                val digit = pin.getOrNull(i)?.toString() ?: ""
                Box(
                    modifier = Modifier
                        .size(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ellipse_181),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = digit,
                        fontSize = 20.sp,
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
