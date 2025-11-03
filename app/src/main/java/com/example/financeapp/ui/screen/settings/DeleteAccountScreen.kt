package com.example.financeapp.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Void

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAccountScreen(
    navController: NavController,
    onBackClick: () -> Unit = { navController.navigateUp() },
    onDeleteConfirmed: (password: String) -> Unit = {},
    onCancel: () -> Unit = { navController.navigateUp() }
) {
    val darkText = Void

    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Caribbean_green)
    ) {
        Column {
            TopBar(
                title = stringResource(R.string.delete_account_title),
                showBackButton = true,
                centerTitle = true,
                onBackClick = onBackClick,
                onNotificationClick = { navController.navigate("notifications") },
                containerColor = Caribbean_green
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp))
                    .background(Honeydew)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, start = 24.dp, end = 24.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(id = R.string.delete_account_confirm_title).ifEmpty { "Are You Sure You Want To Delete\nYour Account?" },
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Void,
                            fontSize = 16.sp,
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = Light_green)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = stringResource(id = R.string.delete_account_warning).ifEmpty {
                                    stringResource(id = R.string.delete_account_confirm_title)                                },
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_light)),
                                    color = Void,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                ),
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            val bulletRowModifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 24.dp)

                            Row(modifier = bulletRowModifier.padding(start = 24.dp)) {
                                Text("•", style = TextStyle(fontFamily = FontFamily(Font(R.font.poppins_light)), color = Void, fontSize = 14.sp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(stringResource(id = R.string.item1), style = TextStyle(fontFamily = FontFamily(Font(R.font.poppins_light)), color = Void, fontSize = 14.sp))
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(modifier = bulletRowModifier.padding(start = 24.dp)) {
                                Text("•", style = TextStyle(fontFamily = FontFamily(Font(R.font.poppins_light)), color = Void, fontSize = 14.sp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(stringResource(id = R.string.item2), style = TextStyle(fontFamily = FontFamily(Font(R.font.poppins_light)), color = Void, fontSize = 14.sp))
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(modifier = bulletRowModifier.padding(start = 24.dp)) {
                                Text("•", style = TextStyle(fontFamily = FontFamily(Font(R.font.poppins_light)), color = Void, fontSize = 14.sp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(stringResource(id = R.string.item3), style = TextStyle(fontFamily = FontFamily(Font(R.font.poppins_light)), color = Void, fontSize = 14.sp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.enter_password_to_confirm).ifEmpty { "Please Enter Your Password To Confirm\nDeletion Of Your Account." },
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = darkText,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(Light_green),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                            TextField(
                                value = password,
                                onValueChange = { password = it },
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color.Transparent),
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.password_hint)
                                    )
                                },
                                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                singleLine = true,
                                textStyle = LocalTextStyle.current.copy(
                                ),
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    cursorColor = Caribbean_green,
                                    unfocusedTextColor = darkText,
                                    focusedTextColor = darkText
                                )
                            )

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    painter = painterResource(id = if (passwordVisible) R.drawable.eye_open else R.drawable.eye_pass),
                                    contentDescription = if (passwordVisible) "Ocultar" else "Mostrar",
                                    tint = darkText,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        onClick = { showConfirmDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Caribbean_green),
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(44.dp)
                            .align(Alignment.CenterHorizontally)
                            .clip(RoundedCornerShape(22.dp)),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Text(text = stringResource(id = R.string.yes_delete_account).ifEmpty { "Yes, Delete Account" },
                            color = Void,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(44.dp)
                            .align(Alignment.CenterHorizontally)
                            .clip(RoundedCornerShape(22.dp))
                            .clickable { onCancel() },
                        color = Light_green
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = stringResource(id = R.string.cancel).ifEmpty { "Cancel" },
                                color = darkText,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))


                }
            }
        }

        // Overlay modal: oscurece la pantalla y muestra la card de confirmación centrada
        if (showConfirmDialog) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f)),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White,
                    modifier = Modifier
                        .padding(24.dp)
                        .widthIn(min = 280.dp, max = 360.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.delete_account_title).ifEmpty { "Delete Account" },
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                                fontSize = 20.sp,
                                color = Void
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = stringResource(id = com.example.financeapp.R.string.logout_confirm_title),
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontSize = 16.sp,
                                color = Void
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = stringResource(id = R.string.message_delete),
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 14.sp,
                                color = Void
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                showConfirmDialog = false
                                onDeleteConfirmed(password)
                                // Navegar a Welcome y limpiar back stack para que sea la pantalla inicial
                                navController.navigate("welcome") {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                             },
                             colors = ButtonDefaults.buttonColors(containerColor = Caribbean_green),
                             modifier = Modifier
                                 .fillMaxWidth(0.6f)
                                 .height(44.dp)
                                 .align(Alignment.CenterHorizontally),
                             shape = RoundedCornerShape(24.dp)
                         ) {
                             Text(
                                 text = stringResource(id = R.string.yes_delete_account).ifEmpty { "Yes, Delete Account" },
                                 color = Void,
                                 fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                 fontSize = 14.sp
                             )
                         }

                        Spacer(modifier = Modifier.height(12.dp))

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(44.dp)
                                .align(Alignment.CenterHorizontally)
                                .clip(RoundedCornerShape(22.dp))
                                .clickable { showConfirmDialog = false },
                            color = Light_green
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = stringResource(id = R.string.cancel).ifEmpty { "Cancel" },
                                    color = Void,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}
