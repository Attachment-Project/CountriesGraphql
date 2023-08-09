package com.example.countryinfo.domain.usecase
import com.example.countryinfo.domain.model.SimpleCountry
import com.example.countryinfo.domain.repositiry.CountryClient

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(): List<SimpleCountry> {
        return countryClient.getCountries()
            .sortedBy { it.name }
    }
}