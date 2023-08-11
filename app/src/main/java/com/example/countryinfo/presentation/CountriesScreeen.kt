package com.example.countryinfo.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countryinfo.domain.model.SimpleCountry

@Composable
fun MainScreenComposable(
    state: CountriesState,
    onCountrySelected: (code: String) -> Unit,
    onDismissSelectedCountry: () -> Unit
) {
    if (state.isLoading) {
        LoadingScreen()
    } else if (state.isError == true) {
        ErrorScreen(errorMessage = "Error")
    } else {
            MainScreen(
                state = state,
                onCountrySelected = { onCountrySelected(it) }
            )
        }

    }



@Composable
fun MainScreen(
    state: CountriesState,
    onCountrySelected: (code: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.countries) {
            CountryItem(
                country = it,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clickable { onCountrySelected(it.code) }
            )
        }
    }
}

    @Composable
    fun LoadingScreen() {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Loading ...", color = MaterialTheme.colorScheme.onBackground)
        }
    }

    @Composable
    fun ErrorScreen(errorMessage: String) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.onBackground)
        }
    }
@Composable
fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = country.emoji, fontSize = 30.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column (modifier = Modifier.weight(1f)){
            Text(text = country.name, color = MaterialTheme.colorScheme.onBackground)
            Text(text = country.capital, color = MaterialTheme.colorScheme.onBackground)
        }
    }

}

