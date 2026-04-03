package com.benjianddaisy.store.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = BJDYSPrimary,
    secondary = BJDYSAccent,
    tertiary = BJDYSAccent,
    background = BJDYSBackground,
    surface = BJDYSSurface,
    onPrimary = BJDYSOnPrimary,
    onSecondary = BJDYSPrimary,
    onTertiary = BJDYSPrimary,
    onBackground = BJDYSOnSurface,
    onSurface = BJDYSOnSurface,
    outline = BJDYSDivider,
    surfaceVariant = BJDYSBackground,
    onSurfaceVariant = BJDYSMutedText,
)

private val DarkColorScheme = darkColorScheme(
    primary = BJDYSAccent,
    secondary = BJDYSAccent,
    tertiary = BJDYSAccent,
    background = BJDYSDarkBackground,
    surface = BJDYSDarkSurface,
    onPrimary = BJDYSPrimary,
    onSecondary = BJDYSPrimary,
    onTertiary = BJDYSPrimary,
    onBackground = BJDYSDarkOnSurface,
    onSurface = BJDYSDarkOnSurface,
    outline = Color(0xFF3D3D3D),
    surfaceVariant = Color(0xFF2A2A2A),
    onSurfaceVariant = Color(0xFFAAAAAA),
)

@Composable
fun ProductAppBJDYSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
