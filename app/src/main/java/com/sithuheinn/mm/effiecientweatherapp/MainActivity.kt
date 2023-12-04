package com.sithuheinn.mm.effiecientweatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sithuheinn.mm.effiecientweatherapp.presentation.WeatherInformationViewModel
import com.sithuheinn.mm.effiecientweatherapp.presentation.composables.WeatherInfoCard
import com.sithuheinn.mm.effiecientweatherapp.ui.theme.DarkBlue
import com.sithuheinn.mm.effiecientweatherapp.ui.theme.DeepBlue
import com.sithuheinn.mm.effiecientweatherapp.ui.theme.EffiecientWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm: WeatherInformationViewModel by viewModels()

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            vm.fetchWeatherInfo()
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )

        setContent {
            EffiecientWeatherAppTheme {
                val vm: WeatherInformationViewModel = hiltViewModel()
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    WeatherInfoCard(
                        state = vm.state
                    )

                    if (vm.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    vm.state.error?.let { error ->
                        Text(
                            text = error,
                            color = androidx.compose.ui.graphics.Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EffiecientWeatherAppTheme {
        Greeting("Android")
    }
}