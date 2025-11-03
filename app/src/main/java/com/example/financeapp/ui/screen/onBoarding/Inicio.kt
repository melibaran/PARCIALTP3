package com.example.financeapp.ui.screen.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.ui.components.PrimaryButton
import com.example.financeapp.ui.components.SecondaryButton
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily

@Composable
fun InicioFinWise(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(Honeydew)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.finwise1),
                contentDescription = "Logo FinWise",
                modifier = Modifier.size(152.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Título FinWise
            Text(
                text = "FinWise",
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 40.sp,
                color = Caribbean_green,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Descripción (lorem) con poco interlineado
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod.",
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 11.sp,
                lineHeight = 13.sp,
                color = Void,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Botón Log In (ancho controlado para igualar el mockup)
            Box(modifier = Modifier.width(260.dp)) {
                PrimaryButton(
                    text = "Log In",
                    onClick = onLoginClick,
                    enabled = true
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Botón Sign Up
            Box(modifier = Modifier.width(260.dp)) {
                SecondaryButton(
                    text = "Sign Up",
                    onClick = onSignUpClick
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot Password? como texto pequeño clickeable sin bordes
            TextButton(onClick = onForgotPasswordClick) {
                Text(
                    text = "Forgot Password?",
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    color = Void
                )
            }
        }
    }
}