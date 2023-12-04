package com.sithuheinn.mm.designsystems.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun WeatherCardText(
    text: String,
    color: Color,
    fontSize: TextUnit,
    modifier: Modifier,
    isNightTime: Boolean = false
) {
    Text(
        modifier = modifier,
        text = text,
        color = if (isNightTime) Color.White else color,
        fontSize = fontSize
    )
}