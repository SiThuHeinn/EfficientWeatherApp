package com.sithuheinn.mm.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.sithuheinn.mm.designsystems.R
import com.sithuheinn.mm.designsystems.theme.DarkRed
import com.sithuheinn.mm.designsystems.theme.DodgerBlue
import com.sithuheinn.mm.designsystems.theme.LightGray
import com.sithuheinn.mm.designsystems.theme.LightSkyBlue
import com.sithuheinn.mm.designsystems.theme.LightSteelBlue
import com.sithuheinn.mm.designsystems.theme.PowderBlue
import com.sithuheinn.mm.designsystems.theme.SaddleBrown
import com.sithuheinn.mm.designsystems.theme.SkyBlue
import com.sithuheinn.mm.designsystems.theme.SteelBlue
import org.threeten.bp.LocalDateTime

/**
 * Weather condition
 *
 * @property weatherDesc  label for weather condition
 * @property iconRes  displayable weather condition icon
 */
sealed class WeatherCondition(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int,
    val backgroundColor: Color,
    val textColor: Color
) {
    object ClearSky: WeatherCondition(
        weatherDesc = "Clear Sky",
        iconRes = R.drawable.ic_sunny,
        backgroundColor = LightSkyBlue,
        textColor = Color.Black
    )

    object MainlyClear: WeatherCondition(
        weatherDesc = "Mainly clear",
        iconRes = R.drawable.ic_cloudy,
        backgroundColor = LightSkyBlue,
        textColor = Color.Black
    )

    object PartlyCloudy: WeatherCondition(
        weatherDesc = "Partly cloudy",
        iconRes = R.drawable.ic_cloudy,
        backgroundColor = LightSkyBlue,
        textColor = Color.Black
    )

    object Overcast: WeatherCondition(
        weatherDesc = "Overcast",
        iconRes = R.drawable.ic_cloudy,
        backgroundColor = LightSteelBlue,
        textColor = Color.Black
    )

    object Foggy: WeatherCondition(
        weatherDesc = "Foggy",
        iconRes = R.drawable.ic_very_cloudy,
        backgroundColor = LightGray,
        textColor = Color.Black
    )

    object DepositingRimeFog : WeatherCondition(
        weatherDesc = "Depositing rime fog",
        iconRes = R.drawable.ic_very_cloudy,
        backgroundColor = LightGray,
        textColor = Color.Black
    )
    object LightDrizzle : WeatherCondition(
        weatherDesc = "Light drizzle",
        iconRes = R.drawable.ic_rainshower,
        backgroundColor = PowderBlue,
        textColor = Color.Black
    )
    object ModerateDrizzle : WeatherCondition(
        weatherDesc = "Moderate drizzle",
        iconRes = R.drawable.ic_rainshower,
        backgroundColor = LightSkyBlue,
        textColor = Color.Black
    )
    object DenseDrizzle : WeatherCondition(
        weatherDesc = "Dense drizzle",
        iconRes = R.drawable.ic_rainshower,
        backgroundColor = SteelBlue,
        textColor = Color.White
    )
    object LightFreezingDrizzle : WeatherCondition(
        weatherDesc = "Slight freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy,
        backgroundColor = LightSkyBlue,
        textColor = Color.Black
    )
    object DenseFreezingDrizzle : WeatherCondition(
        weatherDesc = "Dense freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy,
        backgroundColor = SteelBlue,
        textColor = Color.White
    )
    object SlightRain : WeatherCondition(
        weatherDesc = "Slight rain",
        iconRes = R.drawable.ic_rainy,
        backgroundColor = LightSkyBlue,
        textColor = Color.Black
    )
    object ModerateRain : WeatherCondition(
        weatherDesc = "Rainy",
        iconRes = R.drawable.ic_rainy,
        backgroundColor = SteelBlue,
        textColor = Color.White
    )
    object HeavyRain : WeatherCondition(
        weatherDesc = "Heavy rain",
        iconRes = R.drawable.ic_rainy,
        backgroundColor = DodgerBlue,
        textColor = Color.White
    )
    object HeavyFreezingRain: WeatherCondition(
        weatherDesc = "Heavy freezing rain",
        iconRes = R.drawable.ic_snowyrainy,
        backgroundColor = DodgerBlue,
        textColor = Color.White
    )
    object SlightSnowFall: WeatherCondition(
        weatherDesc = "Slight snow fall",
        iconRes = R.drawable.ic_snowy,
        backgroundColor = SkyBlue,
        textColor = Color.Black
    )
    object ModerateSnowFall: WeatherCondition(
        weatherDesc = "Moderate snow fall",
        iconRes = R.drawable.ic_heavysnow,
        backgroundColor = PowderBlue,
        textColor = Color.Black
    )
    object HeavySnowFall: WeatherCondition(
        weatherDesc = "Heavy snow fall",
        iconRes = R.drawable.ic_heavysnow,
        backgroundColor = SteelBlue,
        textColor = Color.White
    )
    object SnowGrains: WeatherCondition(
        weatherDesc = "Snow grains",
        iconRes = R.drawable.ic_heavysnow,
        backgroundColor = PowderBlue,
        textColor = Color.Black
    )
    object SlightRainShowers: WeatherCondition(
        weatherDesc = "Slight rain showers",
        iconRes = R.drawable.ic_rainshower,
        backgroundColor = LightSkyBlue,
        textColor = Color.Black
    )
    object ModerateRainShowers: WeatherCondition(
        weatherDesc = "Moderate rain showers",
        iconRes = R.drawable.ic_rainshower,
        backgroundColor = SteelBlue,
        textColor = Color.White
    )
    object ViolentRainShowers: WeatherCondition(
        weatherDesc = "Violent rain showers",
        iconRes = R.drawable.ic_rainshower,
        backgroundColor = DodgerBlue,
        textColor = Color.White
    )
    object SlightSnowShowers: WeatherCondition(
        weatherDesc = "Light snow showers",
        iconRes = R.drawable.ic_snowy,
        backgroundColor = SkyBlue,
        textColor = Color.Black
    )
    object HeavySnowShowers: WeatherCondition(
        weatherDesc = "Heavy snow showers",
        iconRes = R.drawable.ic_snowy,
        backgroundColor = SteelBlue,
        textColor = Color.White
    )
    object ModerateThunderstorm: WeatherCondition(
        weatherDesc = "Moderate thunderstorm",
        iconRes = R.drawable.ic_thunder,
        backgroundColor = DarkRed,
        textColor = Color.White
    )
    object SlightHailThunderstorm: WeatherCondition(
        weatherDesc = "Thunderstorm with slight hail",
        iconRes = R.drawable.ic_rainythunder,
        backgroundColor = SaddleBrown,
        textColor = Color.White
    )
    object HeavyHailThunderstorm: WeatherCondition(
        weatherDesc = "Thunderstorm with heavy hail",
        iconRes = R.drawable.ic_rainythunder,
        backgroundColor = SaddleBrown,
        textColor = Color.White
    )


    companion object {
        fun fromWMO(code: Int): WeatherCondition {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }

}