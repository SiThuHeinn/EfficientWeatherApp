package com.sithuheinn.mm.effiecientweatherapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sithuheinn.mm.effiecientweatherapp.presentation.UiDataState
import org.threeten.bp.format.DateTimeFormatter


@Composable
fun WeatherInfoCard(
    state: UiDataState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {

    state.displayableWeatherDataModel?.currentWeatherData?.let { data ->
        Card(
            colors = CardDefaults.cardColors(backgroundColor),
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today ${
                        data.time.format(
                            DateTimeFormatter.ofPattern("HH:mm")
                        )
                    }",
                    modifier = Modifier.align(Alignment.End),
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "My Location",
                    color = Color.White,
                    fontSize = 28.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = data.weatherCondition.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(100.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${data.temperatureCelsius}°C",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }


    } ?: kotlin.run {

    }

}