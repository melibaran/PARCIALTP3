package com.example.financeapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.financeapp.R
import com.example.financeapp.ui.theme.Light_blue
import com.example.financeapp.ui.theme.Vivid_blue

/**
 * Componente reutilizable para mostrar una categoría de gastos
 *
 * Implementa el patrón de diseño State Hoisting y maneja estados interactivos (pressed/default)
 *
 * @param icon Resource ID del icono en estado default
 * @param name Nombre de la categoría a mostrar
 * @param onClick Callback ejecutado al hacer click en el item
 *
 * @sample
 * CategoryItem(
 *     icon = R.drawable.food_default,
 *     name = "Food",
 *     onClick = { navController.navigate("food") }
 * )
 */
@Composable
fun CategoryItem(
    icon: Int,
    name: String,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val iconResId = when {
        isPressed -> getCategoryIconPressed(name)
        else -> icon
    }

    val backgroundColor = if (isPressed) Vivid_blue else Light_blue

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = name,
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = name, style = MaterialTheme.typography.bodyMedium)
    }
}

private fun getCategoryIconPressed(name: String): Int {
    return when (name) {
        "Food" -> R.drawable.food_pressed
        "Transport" -> R.drawable.transport_pressed
        "Medicine" -> R.drawable.medicine_pressed
        "Groceries" -> R.drawable.groceries_pressed
        "Rent" -> R.drawable.rent_pressed
        "Gift" -> R.drawable.gift_pressed
        "Entertainment" -> R.drawable.entertaiment_pressed
        "Savings" -> R.drawable.saving_pressed
        else -> R.drawable.food_pressed
    }
}

