package com.example.financeapp.ui.screen.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.ui.components.TopBar
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryAddExpensesButton
import com.example.financeapp.ui.screen.categories.arquitectura.CategoryDatePicker
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Honeydew
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Void

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
    var showDatePicker by remember { mutableStateOf(false) }

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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Caribbean_green)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Caribbean_green),
                contentAlignment = Alignment.Center
            ) {
                TopBar(
                    title = stringResource(id = R.string.add_expenses),
                    showBackButton = true,
                    centerTitle = true,
                    onBackClick = { navController.navigateUp() },
                    onNotificationClick = { navController.navigate("notifications") },
                    containerColor = Color.Transparent
                )
            }


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
                colors = CardDefaults.cardColors(containerColor = Honeydew)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState())
                )
   {
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
                     Spacer(modifier = Modifier.height(20.dp))
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
                            Spacer(modifier = Modifier.height(20.dp))
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
                        Spacer(modifier = Modifier.height(20.dp))
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
                    Spacer(modifier = Modifier.height(16.dp))

                    Column {
                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(166.dp),
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.enter_message),
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
                    Spacer(modifier = Modifier.height(28.dp))
                CategoryAddExpensesButton(
                    onClick = {navController.navigateUp()},
                    tittle = "Save"
                )
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

