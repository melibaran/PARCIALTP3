package com.example.financeapp.ui.screen.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpensesScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var date by remember { mutableStateOf("April 30 ,2024") }
    var selectedCategory by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("$26,00") }
    var expenseTitle by remember { mutableStateOf("Dinner") }
    var message by remember { mutableStateOf("") }
    var showCategoryDropdown by remember { mutableStateOf(false) }

    val categories = listOf(
        "Food",
        "Transport",
        "Medicine",
        "Shopping",
        "Rent",
        "Gifts",
        "Saving",
        "Entertainment"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.add_expenses),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Void,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(R.drawable.bring_back),
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(19.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("notifications") }) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.White, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.bell),
                                contentDescription = "Notifications",
                                modifier = Modifier.size(24.dp),
                                tint = Void
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Caribbean_green
                )
            )
        },
        bottomBar = {
            // Bottom bar con botón Save centrado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                   //.background(Honeydew)
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier
                        .width(169.dp)
                        .height(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Caribbean_green
                    ),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.save_button),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                            color = Honeydew,
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
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Honeydew
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Date Field
                    Column {
                        Text(
                            text = stringResource(id = R.string.date_label),
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
                                IconButton(onClick = { /* TODO: Implementar DatePicker */ }) {
                                    Icon(
                                        painter = painterResource(R.drawable.calendario),
                                        contentDescription = "Select date",
                                        tint = Caribbean_green,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            singleLine = true
                        )
                    }

                    // Category Dropdown
                    Column {
                        Text(
                            text = stringResource(id = R.string.category_label),
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Void,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        ExposedDropdownMenuBox(
                            expanded = showCategoryDropdown,
                            onExpandedChange = { showCategoryDropdown = it }
                        ) {
                            OutlinedTextField(
                                value = selectedCategory,
                                onValueChange = {},
                                readOnly = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                                placeholder = {
                                    Text(
                                        stringResource(id = R.string.select_category),
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
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = showCategoryDropdown)
                                },
                                singleLine = true
                            )
                            ExposedDropdownMenu(
                                expanded = showCategoryDropdown,
                                onDismissRequest = { showCategoryDropdown = false }
                            ) {
                                categories.forEach { category ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                category,
                                                style = TextStyle(
                                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                    fontSize = 14.sp,
                                                )
                                            )
                                        },
                                        onClick = {
                                            selectedCategory = category
                                            showCategoryDropdown = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Amount Field
                    Column {
                        Text(
                            text = stringResource(id = R.string.amount_label),
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

                    // Expense Title Field
                    Column {
                        Text(
                            text = stringResource(id = R.string.expense_title_label),
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Void,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = expenseTitle,
                            onValueChange = { expenseTitle = it },
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
                                    stringResource(id = R.string.enter_message),
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
                            maxLines = 6
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

