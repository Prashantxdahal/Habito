package com.example.habito.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Blue = Color(0xFF1565C0)
val LightBlue = Color(0xFF64B5F6)
val PeaceBlue = Color(0xFFE3F2FD)

private val LightColors = lightColorScheme(
    primary = Blue,
    secondary = LightBlue,
    background = PeaceBlue,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Blue,
    onSurface = Blue
)

@Composable
fun HabitoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(
            bodyLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            titleLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        ),
        content = content
    )
}
