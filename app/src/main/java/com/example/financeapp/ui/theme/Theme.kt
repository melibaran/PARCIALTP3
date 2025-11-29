package com.example.financeapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = CaribbeanGreenBase,
    onPrimary = VoidBase,
    primaryContainer = LightGreenBase,
    onPrimaryContainer = VoidBase,
    secondary = LightGreenBase,
    onSecondary = VoidBase,
    secondaryContainer = HoneydewBase,
    onSecondaryContainer = VoidBase,
    tertiary = Ocean_blue,
    onTertiary = Color.White,
    tertiaryContainer = Light_blue,
    onTertiaryContainer = VoidBase,
    background = CaribbeanGreenBase,
    onBackground = VoidBase,
    surface = HoneydewBase,
    onSurface = VoidBase,
    surfaceVariant = LightGreenBase,
    onSurfaceVariant = VoidBase,
    outline = VoidBase.copy(alpha = 0.4f),
    outlineVariant = VoidBase.copy(alpha = 0.2f),
    scrim = VoidBase.copy(alpha = 0.8f)
)

private val DarkColorScheme = darkColorScheme(
    primary = CaribbeanGreenBase,
    onPrimary = FenceGreenBase,
    primaryContainer = Deep_teal,
    onPrimaryContainer = Color.White,
    secondary = Deep_teal,
    onSecondary = Color.White,
    secondaryContainer = FenceGreenBase,
    onSecondaryContainer = Color.White,
    tertiary = Ocean_blue,
    onTertiary = Color.White,
    tertiaryContainer = Light_blue,
    onTertiaryContainer = Color.White,
    background = FenceGreenBase,
    onBackground = Color.White,
    surface = Deep_teal,
    onSurface = Color.White,
    surfaceVariant = FenceGreenBase,
    onSurfaceVariant = Color.White,
    outline = Color.White.copy(alpha = 0.3f),
    outlineVariant = Color.White.copy(alpha = 0.15f),
    scrim = Color.Black.copy(alpha = 0.7f)
)

data class ThemeController(
    val isDarkMode: Boolean,
    val toggleDarkMode: (Boolean) -> Unit
)

val LocalThemeController = staticCompositionLocalOf {
    ThemeController(
        isDarkMode = false,
        toggleDarkMode = {}
    )
}

@Composable
fun FinanceAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}