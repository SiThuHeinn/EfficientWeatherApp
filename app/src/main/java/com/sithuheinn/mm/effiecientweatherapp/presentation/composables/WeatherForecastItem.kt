package com.sithuheinn.mm.effiecientweatherapp.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import com.sithuheinn.mm.domain.model.WeatherDataModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.TextStyle
import java.util.Locale


@Composable
fun WeatherForecastItem(
    modifier: Modifier = Modifier,
    data: List<WeatherDataModel>,
    titleTextColor: Color,
    dayOfWeek: Long = 0
) {

    val now = LocalDateTime.now()
    val date = now.dayOfWeek.plus(dayOfWeek).getDisplayName(TextStyle.FULL, Locale.US)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp),
            text = if (dayOfWeek == 0L) "Today" else date,
            color = titleTextColor
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White)
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 10.dp),
            content = {
                items(data) { model ->
                    HourlyDisplay(
                        model = model,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    )
                }
            })
        
        Spacer(modifier = Modifier.height(8.dp))
    }
}