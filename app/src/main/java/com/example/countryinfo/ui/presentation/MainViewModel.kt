package com.example.countryinfo.ui.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryinfo.domain.model.SimpleCountry
import com.example.countryinfo.domain.usecase.GetCountryUseCase
import com.example.countryinfo.domain.usecase.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCountriesUseCase : GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
): ViewModel() {
    init {
        viewModelScope.launch {
            val countries = getCountriesUseCase.execute()
            Log.d("MainViewModel", "Countries: $countries")
        }
    }
}