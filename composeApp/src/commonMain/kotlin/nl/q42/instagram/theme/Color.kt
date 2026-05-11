package nl.q42.instagram.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Define colors for Material3 theme
private val PrimaryColor = Color(0xFFE1306C) // Instagram pink
private val SecondaryColor = Color(0xFF405DE6) // Instagram blue
private val TertiaryColor = Color(0xFFFeda75) // Instagram yellow

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,
)

fun getColorScheme(isDark: Boolean = false): ColorScheme {
    return if (isDark) DarkColorScheme else LightColorScheme
}

