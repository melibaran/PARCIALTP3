package com.example.financeapp.ui.screen.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryDatePicker
import com.example.financeapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSavingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var date by remember { mutableStateOf("April 30 ,2024") }
    var selectedGoal by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("$26,00") }
    var depositTitle by remember { mutableStateOf("Deposit") }
    var message by remember { mutableStateOf("") }
    var showGoalDropdown by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    val savingsGoals = listOf(
        "Travel",
        "New House",
        "Car",
        "Wedding"
    )

    Scaffold(
        topBar = {
            TopBar(
                title = "Add Savings",
                showBackButton = true,
                centerTitle = true,
                onBackClick = { navController.navigateUp() },
                onNotificationClick = { navController.navigate("notifications") },
                containerColor = Caribbean_green
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Honeydew)
                    .padding(bottom = 24.dp, top = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Button(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier
                        .padding(horizontal = 95.dp)
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Caribbean_green
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Save",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                            color = Void,
                            fontSize = 16.sp,
                        )
                    )
                }
            }
        },
        containerColor = Caribbean_green
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Card principal con fondo Honeydew
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(topStart = 44.dp, topEnd = 44.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Honeydew
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Date Field
                    Column {
                        Text(
                            text = "Date",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Void,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = date,
                            onValueChange = { date = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            textStyle = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Void,
                                fontSize = 14.sp,
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Light_green,
                                unfocusedContainerColor = Light_green,
                                focusedBorderColor = Caribbean_green,
                                unfocusedBorderColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(18.dp),
                            trailingIcon = {
                                val calendarInteractionSource = remember { MutableInteractionSource() }
                                val isCalendarPressed by calendarInteractionSource.collectIsPressedAsState()

                                val calendarIcon = if (isCalendarPressed) {
                                    R.drawable.calendar_pressed
                                } else {
                                    R.drawable.calendar_default
                                }

                                IconButton(
                                    onClick = { showDatePicker = true },
                                    interactionSource = calendarInteractionSource
                                ) {
                                    Icon(
                                        painter = painterResource(calendarIcon),
                                        contentDescription = "Select date",
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            singleLine = true
                        )
                    }

                    // Savings Goal Dropdown
                    Column {
                        Text(
                            text = "Savings Goal",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Void,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        ExposedDropdownMenuBox(
                            expanded = showGoalDropdown,
                            onExpandedChange = { showGoalDropdown = it }
                        ) {
                            OutlinedTextField(
                                value = selectedGoal,
                                onValueChange = {},
                                readOnly = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                                placeholder = {
                                    Text(
                                        "Select a savings goal",
                                        style = TextStyle(
                                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                            color = Caribbean_green,
                                            fontSize = 14.sp,
                                        )
                                    )
                                },
                                textStyle = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = Void,
                                    fontSize = 14.sp,
                                ),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Light_green,
                                    unfocusedContainerColor = Light_green,
                                    focusedBorderColor = Caribbean_green,
                                    unfocusedBorderColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(18.dp),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = showGoalDropdown)
                                },
                                singleLine = true
                            )
                            ExposedDropdownMenu(
                                expanded = showGoalDropdown,
                                onDismissRequest = { showGoalDropdown = false }
                            ) {
                                savingsGoals.forEach { goal ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                goal,
                                                style = TextStyle(
                                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                    fontSize = 14.sp,
                                                )
                                            )
                                        },
                                        onClick = {
                                            selectedGoal = goal
                                            showGoalDropdown = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Amount Field
                    Column {
                        Text(
                            text = "Amount",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Void,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = amount,
                            onValueChange = { amount = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            textStyle = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Void,
                                fontSize = 14.sp,
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Light_green,
                                unfocusedContainerColor = Light_green,
                                focusedBorderColor = Caribbean_green,
                                unfocusedBorderColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(18.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            singleLine = true
                        )
                    }

                    // Deposit Title Field
                    Column {
                        Text(
                            text = "Deposit Title",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Void,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = depositTitle,
                            onValueChange = { depositTitle = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            textStyle = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Void,
                                fontSize = 14.sp,
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Light_green,
                                unfocusedContainerColor = Light_green,
                                focusedBorderColor = Caribbean_green,
                                unfocusedBorderColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(18.dp),
                            singleLine = true
                        )
                    }

                    // Message Field (multiline)
                    Column {
                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(166.dp),
                            placeholder = {
                                Text(
                                    text = "Enter a message (optional)",
                                    style = TextStyle(
                                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                        fontWeight = FontWeight.Medium,
                                        color = Caribbean_green,
                                        fontSize = 15.sp,
                                        lineHeight = 15.sp,
                                        letterSpacing = 0.sp
                                    )
                                )
                            },
                            textStyle = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontWeight = FontWeight.Medium,
                                color = Void,
                                fontSize = 15.sp,
                                lineHeight = 15.sp,
                                letterSpacing = 0.sp
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Light_green,
                                unfocusedContainerColor = Light_green,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(18.dp),
                            maxLines = 6
                        )
                    }
                }
            }
        }
    }

    // DatePicker Dialog - Usando el componente reutilizable
    CategoryDatePicker(
        showDatePicker = showDatePicker,
        onDismiss = { showDatePicker = false }
    )
}

