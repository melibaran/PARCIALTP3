package com.example.financeapp.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.financeapp.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val poppinsFamily = FontFamily(
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val Typography = Typography(
    // Título principal "Transaction"
    titleLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),

    // "Total Balance" (título en card)
    titleMedium = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.sp
    ),

    // Monto grande "$7,783.00"
    headlineLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    // Texto pequeño "Total Balance" y "Total Expense"
    labelSmall = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp
    ),

    // Texto de porcentaje "30% of your expenses"
    bodyMedium = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.sp
    ),

    // Fechas y horas de transacciones
    labelMedium = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.sp,
        color = Light_blue
    )
)

// Estilos personalizados adicionales
val transactionTitle = TextStyle(
    fontFamily = poppinsFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp,
    lineHeight = 15.sp,
    letterSpacing = 0.sp
)

val titleMonth = TextStyle(
    fontFamily = poppinsFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.sp
)

val categoryText = TextStyle(
    fontFamily = poppinsFamily,
    fontWeight = FontWeight.Light,
    fontSize = 13.sp,
    lineHeight = 15.sp,
    letterSpacing = 0.sp
)

val amountText = TextStyle(
    fontFamily = poppinsFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp,
    lineHeight = 15.sp,
    letterSpacing = 0.sp
)
