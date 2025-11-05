package com.example.financeapp.ui.screen.categories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.financeapp.ui.components.PrimaryButton
import com.example.financeapp.ui.components.SecondaryButton
import com.example.financeapp.ui.theme.Caribbean_green
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily

@Composable
fun NewCategoryDialog(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var categoryName by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 42.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "New Category",
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Void,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Input de texto para el nombre de la categoría
                TextField(  value = categoryName,
                    onValueChange = { categoryName = it },
                    placeholder = {
                        Text(
                            text = "Write..",
                            fontFamily = poppinsFamily,
                            fontSize = 14.sp,
                            color = Caribbean_green
                        )

                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Light_green,
                        unfocusedContainerColor = Light_green,
                        disabledContainerColor = Light_green,
                        focusedIndicatorColor = Light_green,
                        unfocusedIndicatorColor = Light_green,
                        ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(25.dp))

                // Botón primario: Save
                PrimaryButton(
                    text = "Save",
                    onClick = {
                        if (categoryName.isNotBlank()) {
                            onConfirm(categoryName)
                        }
                    },
                    enabled = categoryName.isNotBlank()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Botón secundario: Cancel
                SecondaryButton(text = "Cancel", onClick = onDismiss)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

