@file:OptIn(ExperimentalMaterial3Api::class)

package com.sithuheinn.mm.effiecientweatherapp

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sithuheinn.mm.effiecientweatherapp.presentation.WeatherInformationViewModel
import com.sithuheinn.mm.effiecientweatherapp.presentation.composables.LocationSearchBottomSheet
import com.sithuheinn.mm.effiecientweatherapp.presentation.composables.WeatherInfoCard
import com.sithuheinn.mm.effiecientweatherapp.ui.theme.EffiecientWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
                val scaffoldState = rememberBottomSheetScaffoldState()
                val scope = rememberCoroutineScope()

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContent = {
                        Column {
                            LocationSearchBottomSheet(vm.getCityList()) {
                                scope.launch { scaffoldState.bottomSheetState.partialExpand() }
                                vm.onLocationSelected(it)
                                Toast.makeText(this@MainActivity, "Selected ${it.name}", Toast.LENGTH_SHORT).show()
                            }
                        }

                    },
                    sheetPeekHeight = 0.dp
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        WeatherInfoCard(
                            state = vm.state,
                            city = vm.city
                        ) {
                            //vm.fetchWeatherInfo()
                            scope.launch { scaffoldState.bottomSheetState.expand() }
                        }

                        if (vm.state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        vm.state.error?.let { error ->
                            Column(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = error,
                                    color = androidx.compose.ui.graphics.Color.Red,
                                    textAlign = TextAlign.Center,
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                    onClick = { vm.fetchWeatherInfo() }
                                ) {
                                    Text(
                                        text = "Try again",
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }

                        }
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