package nl.q42.instagram.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    isDark: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = getColorScheme(isDark),
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}

