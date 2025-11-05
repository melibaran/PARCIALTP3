package com.example.financeapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


//para setear el font por ejemplo, esta bueno hacerlo aca directo!!
//ir siempre a googleFonts para elegir una font

private val LightColorScheme = lightColorScheme(
    background = Caribbean_green,
)

private val DarkColorScheme = darkColorScheme(
    background = Fence_green,
    surface = Fence_green,
    onBackground = Color.White,
    onSurface = Color.White,
    primary = Caribbean_green,
    onPrimary = Color.White,
    secondary = Light_green,
    onSecondary = Fence_green,
)

// CompositionLocal para compartir el estado del dark mode
data class DarkModeState(
    val isDarkMode: Boolean,
    val setDarkMode: (Boolean) -> Unit
)

val LocalDarkMode = compositionLocalOf<DarkModeState> {
    error("No DarkModeState provided")
}

@Composable
fun FinanceAppTheme(
    darkModeState: DarkModeState? = null, // Estado opcional desde fuera
    dynamicColor: Boolean = false, // Desactivar dynamic color para usar nuestros colores
    content: @Composable () -> Unit
) {
    // Si no se proporciona estado externo, usar uno local (para previews)
    val localDarkModeState = remember { mutableStateOf(false) }
    val effectiveDarkModeState = darkModeState ?: DarkModeState(
        isDarkMode = localDarkModeState.value,
        setDarkMode = { localDarkModeState.value = it }
    )

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (effectiveDarkModeState.isDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        effectiveDarkModeState.isDarkMode -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(LocalDarkMode provides effectiveDarkModeState) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}