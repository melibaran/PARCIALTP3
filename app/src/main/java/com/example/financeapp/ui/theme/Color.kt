package com.example.financeapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val HoneydewBase = Color(0xFFF1FFF3)
val LightGreenBase = Color(0xFFDFF7E2)
val CaribbeanGreenBase = Color(0xFF00D09E)
val Cyprus = Color(0xFF0E3E3E)
val FenceGreenBase = Color(0xFF052224)
val VoidBase = Color(0xFF031314)
val Deep_teal = Color(0xFF093030)
val Light_blue = Color(0xFF6DB6FE)
val Vivid_blue = Color(0xFF3299FF)
val Ocean_blue = Color(0xFF0068FF)

val Honeydew: Color
    @Composable
    get() = MaterialTheme.colorScheme.surface

val Light_green: Color
    @Composable
    get() = MaterialTheme.colorScheme.surfaceVariant

val Caribbean_green: Color
    @Composable
    get() = MaterialTheme.colorScheme.primary

val Fence_green = FenceGreenBase

val Void: Color
    @Composable
    get() = MaterialTheme.colorScheme.onBackground
