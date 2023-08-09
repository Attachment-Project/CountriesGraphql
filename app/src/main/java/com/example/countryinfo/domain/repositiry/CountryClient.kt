package com.example.countryinfo.domain.repositiry

import com.example.countryinfo.domain.model.DetailedCountry
import com.example.countryinfo.domain.model.SimpleCountry

interface CountryClient {
  suspend fun getCountries(): List<SimpleCountry>
  suspend fun getCountry(code: String): DetailedCountry?
}