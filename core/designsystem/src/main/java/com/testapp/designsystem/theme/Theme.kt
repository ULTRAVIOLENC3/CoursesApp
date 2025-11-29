package com.testapp.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Green10,
    onPrimary = White10,
    background = Dark10,
    onBackground = White10,
    surface = DarkGray,
    onSurface = White10,
    surfaceVariant = LightGray10,
    outline = Stroke10,
    secondary = Glass10
)

@Composable
fun CoursesAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}