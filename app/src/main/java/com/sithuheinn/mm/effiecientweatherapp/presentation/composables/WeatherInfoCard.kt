package com.sithuheinn.mm.effiecientweatherapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import com.sithuheinn.mm.designsystems.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sithuheinn.mm.designsystems.composables.WeatherConditionItem
import com.sithuheinn.mm.effiecientweatherapp.presentation.UiDataState
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import kotlin.math.roundToInt


@Composable
fun WeatherInfoCard(
    state: UiDataState,
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit
) {

    val backgroundColor = state
        .displayableWeatherDataModel
        ?.currentWeatherData
        ?.weatherCondition
        ?.backgroundColor?.copy(alpha = 0.8f)
        ?: Color.White


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        state.displayableWeatherDataModel?.currentWeatherData?.let { data ->
            Card(
                colors = CardDefaults.cardColors(data.weatherCondition.backgroundColor),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier.padding(top = 16.dp, bottom = 5.dp, start = 16.dp, end = 16.dp),
                elevation = CardDefaults.cardElevation(2.dp)
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
                                DateTimeFormatter.ofPattern("h:mm a")
                            )
                        }",
                        modifier = Modifier.align(Alignment.End),
                        color = data.weatherCondition.textColor
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "My Location",
                        color = data.weatherCondition.textColor,
                        fontSize = 28.sp,
                        modifier = Modifier
                            .clickable(onClick = onLocationClick)
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
                        color = data.weatherCondition.textColor,
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = data.weatherCondition.weatherDesc,
                        color = data.weatherCondition.textColor,
                        fontSize = 20.sp
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(data.weatherCondition.backgroundColor),
                shape = RoundedCornerShape(10.dp),
                modifier = modifier.padding(top = 5.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WeatherConditionItem(
                        value = data.pressure.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(id = com.sithuheinn.mm.designsystems.R.drawable.ic_pressure),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = data.weatherCondition.textColor)
                    )
                    WeatherConditionItem(
                        value = data.humidity.roundToInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(id = com.sithuheinn.mm.designsystems.R.drawable.ic_drop),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = data.weatherCondition.textColor)
                    )
                    WeatherConditionItem(
                        value = data.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(com.sithuheinn.mm.designsystems.R.drawable.ic_wind),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = data.weatherCondition.textColor)
                    )
                }
            }
            WeatherForecast(state = state)


        } ?: kotlin.run {

        }
    }

}