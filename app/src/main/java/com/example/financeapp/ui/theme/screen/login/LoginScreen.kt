package com.example.financeapp.ui.theme.screen.login
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions


import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Cyprus
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily


@Composable
fun LoginScreen(onLoginClick: () -> Unit = {},
                onSignUpClick: () -> Unit = {},
                onForgotPasswordClick: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Caribbean_green)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = "Welcome",
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
                colors = CardDefaults.cardColors(containerColor = Honeydew)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "Username Or Email",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Void,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("example@example.com", fontFamily = poppinsFamily, color = Color.Gray) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Caribbean_green,
                            unfocusedBorderColor = Light_green,
                            unfocusedContainerColor = Light_green,
                            focusedContainerColor = Light_green
                        )
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "Password",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Void,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("**********", fontFamily = poppinsFamily, color = Color.Gray) },
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Caribbean_green,
                            unfocusedBorderColor = Light_green,
                            unfocusedContainerColor = Light_green,
                            focusedContainerColor = Light_green
                        ),
                        trailingIcon = {
                            val image = if (passwordVisible)
                                painterResource(id= R.drawable.ojoabierto)
                            else painterResource(id= R.drawable.ojocerrado)

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(painter = image, contentDescription = "Toggle password visibility", tint = Cyprus)
                            }
                        }
                    )

                    Spacer(Modifier.height(24.dp))

                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Caribbean_green),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Log In", color = Color.White, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold)
                    }

                    TextButton(onClick = onForgotPasswordClick) {
                        Text("Forgot Password?", color = Cyprus, fontFamily = poppinsFamily)
                    }

                    Button(
                        onClick = onSignUpClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Light_green),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Sign Up", color = Cyprus, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold)
                    }

                    TextButton(onClick = { /* TODO: Fingerprint */ }) {
                        Text("Use Fingerprint To Access", color = Caribbean_green, fontFamily = poppinsFamily, fontWeight = FontWeight.SemiBold)
                    }

                    Spacer(Modifier.height(16.dp))

                    Text("or sign up with", color = Color.Gray, fontFamily = poppinsFamily)

                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Placeholder for Facebook logo
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground), // TODO: Replace with your Facebook logo
                            contentDescription = "Facebook",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                        // Placeholder for Google logo
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground), // TODO: Replace with your Google logo
                            contentDescription = "Google",
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Row {
                        Text("Don't have an account?", color = Color.Gray, fontFamily = poppinsFamily)
                        TextButton(onClick = onSignUpClick) {
                            Text("Sign Up", color = Caribbean_green, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}