package com.example.countryinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countryinfo.presentation.CountriesScreen
import com.example.countryinfo.presentation.MainViewModel
import com.example.countryinfo.ui.theme.CountryInfoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryInfoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<MainViewModel>()
                    val state by viewModel.state.collectAsState()
                    CountriesScreen(
                        state = state,
                        onCountrySelected = {
                            viewModel::selectCountry
                        },
                        onDismissSelectedCountry = {
                            viewModel::dismissSelectedCountry
                        }
                    )
                }
            }
        }
    }
}

