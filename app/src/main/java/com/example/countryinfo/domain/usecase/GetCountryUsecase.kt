package com.example.countryinfo.domain.usecase

import com.example.countryinfo.domain.model.DetailedCountry
import com.example.countryinfo.domain.repositiry.CountryClient

class GetCountryUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(code: String): DetailedCountry? {
        return countryClient.getCountry(code)
    }
}