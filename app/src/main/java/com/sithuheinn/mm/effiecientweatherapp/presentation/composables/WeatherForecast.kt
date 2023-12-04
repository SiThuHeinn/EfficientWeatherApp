package com.sithuheinn.mm.effiecientweatherapp.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sithuheinn.mm.effiecientweatherapp.presentation.UiDataState


@Composable
fun WeatherForecast(
    state: UiDataState,
    modifier: Modifier = Modifier
) {
    state.displayableWeatherDataModel?.weatherDataPerDay?.get(0)?.let { data ->

        val cardColor = state.displayableWeatherDataModel.currentWeatherData?.weatherCondition?.backgroundColor ?: Color.White
        val titleTextColor = state.displayableWeatherDataModel.currentWeatherData?.weatherCondition?.textColor ?: Color.White

        Card(
            colors = CardDefaults.cardColors(cardColor),
            shape = RoundedCornerShape(10.dp),
            modifier = modifier
                .padding(top = 16.dp, bottom = 5.dp, start = 16.dp, end = 16.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 10.dp),
                    text = "Today",
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
            }
        }
    }
}