package com.example.countryinfo.presentation

import com.example.countryinfo.domain.model.DetailedCountry
import com.example.countryinfo.domain.model.SimpleCountry

data class CountriesState(
    val isLoading: Boolean = true,
    val countries: List<SimpleCountry> = emptyList(),
    val selectedCountry: DetailedCountry? = null,
    val errorMessage:String? = null
)