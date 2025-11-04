package com.example.financeapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.financeapp.ui.theme.Caribbean_green

@Composable
fun Item1(){
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Dot activo
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(Caribbean_green)
        )
        // Dot inactivo (solo borde)
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .border(2.dp, Caribbean_green, CircleShape)
                .background(Color.Transparent)
        )
    }
}

@Composable
fun Item2(){
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .border(2.dp, Caribbean_green, CircleShape)
                .background(Color.Transparent)
        )
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(Caribbean_green)
        )
    }
}