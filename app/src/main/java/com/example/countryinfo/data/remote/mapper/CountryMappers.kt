package com.example.countryinfo.data.remote.mapper

import com.example.countryinfo.CountriesQuery
import com.example.countryinfo.CountryQuery
import com.example.countryinfo.domain.model.DetailedCountry
import com.example.countryinfo.domain.model.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        name = name,
        code = code,
        emoji = emoji,
        capital = capital?:"No capital",
        currency = currency?: "No currency",
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital?:"No capital",
    )
}