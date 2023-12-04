package com.sithuheinn.mm.effiecientweatherapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sithuheinn.mm.domain.model.WeatherDataModel
import org.threeten.bp.format.DateTimeFormatter


@Composable
fun HourlyDisplay(
    model: WeatherDataModel,
    modifier: Modifier = Modifier,
) {
    val formattedTime = remember(model) {
        model.time.format(
            DateTimeFormatter.ofPattern("h:mm a")
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formattedTime,
            color = model.weatherCondition.textColor
        )
        Image(
            painter = painterResource(id = model.weatherCondition.iconRes),
            contentDescription = null,
            modifier = Modifier.width(35.dp)
        )
        Text(
            text = "${model.temperatureCelsius}°C",
            color = model.weatherCondition.textColor,
        )
    }
}