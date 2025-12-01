package com.example.financeapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Dimensiones estandarizadas del diseño
 * Patrón: Design Tokens para consistencia visual
 */
object Dimens {
    // Spacing
    val SpacingXSmall = 4.dp
    val SpacingSmall = 8.dp
    val SpacingMedium = 12.dp
    val SpacingNormal = 16.dp
    val SpacingLarge = 24.dp
    val SpacingXLarge = 32.dp
    val SpacingXXLarge = 44.dp

    // Icon Sizes
    val IconSizeSmall = 16.dp
    val IconSizeMedium = 24.dp
    val IconSizeLarge = 40.dp
    val IconSizeXLarge = 48.dp
    val IconSizeCircle = 64.dp

    // Corner Radius
    val CornerRadiusSmall = 4.dp
    val CornerRadiusMedium = 13.dp
    val CornerRadiusLarge = 18.dp
    val CornerRadiusXLarge = 24.dp
    val CornerRadiusRounded = 44.dp

    // Button Sizes
    val ButtonHeightSmall = 36.dp
    val ButtonHeightMedium = 48.dp
    val ButtonWidthNormal = 169.dp

    // Progress Bar
    val ProgressBarHeight = 20.dp
    val ProgressBarHeightLarge = 28.dp
}

/**
 * Dimensiones adaptativas basadas en el tamaño de pantalla
 */
object AdaptiveDimens {
    @Composable
    fun adaptiveIconSize(): Dp {
        val config = LocalConfiguration.current
        return when {
            config.screenWidthDp < 600 -> 24.dp // Pantallas pequeñas
            config.screenWidthDp < 840 -> 32.dp // Pantallas medianas
            else -> 40.dp // Pantallas grandes/tabletas
        }
    }

    @Composable
    fun adaptiveSpacing(): Dp {
        val config = LocalConfiguration.current
        return when {
            config.screenWidthDp < 600 -> 8.dp
            config.screenWidthDp < 840 -> 12.dp
            else -> 16.dp
        }
    }

    @Composable
    fun adaptiveCardPadding(): Dp {
        val config = LocalConfiguration.current
        return when {
            config.screenWidthDp < 600 -> 12.dp
            config.screenWidthDp < 840 -> 16.dp
            else -> 24.dp
        }
    }

    @Composable
    fun adaptiveButtonHeight(): Dp {
        val config = LocalConfiguration.current
        return when {
            config.screenWidthDp < 600 -> 48.dp
            config.screenWidthDp < 840 -> 52.dp
            else -> 55.dp
        }
    }
}

/**
 * Tamaños de fuente estandarizados
 */
object FontSizes {
    val ExtraSmall = 11.sp
    val Small = 12.sp
    val Medium = 14.sp
    val Normal = 15.sp
    val Large = 16.sp
    val XLarge = 18.sp
    val XXLarge = 20.sp
    val Title = 24.sp
    val Display = 26.sp
}
