package com.example.countryinfo.data.remote.repository

import com.apollographql.apollo3.ApolloClient
import com.example.countryinfo.CountriesQuery
import com.example.countryinfo.CountryQuery
import com.example.countryinfo.data.remote.mapper.toDetailedCountry
import com.example.countryinfo.data.remote.mapper.toSimpleCountry
import com.example.countryinfo.domain.model.DetailedCountry
import com.example.countryinfo.domain.model.SimpleCountry
import com.example.countryinfo.domain.repositiry.CountryClient
import java.io.IOException

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry>? {
       return try {
           apolloClient
               .query(CountriesQuery())
               .execute()
               .data
               ?.countries
               ?.map {
                   it.toSimpleCountry()
               }?: emptyList()
       }catch (e:IOException){
           e.printStackTrace()
           null
       }
       catch (e:Exception){
           e.printStackTrace()
           null
       }
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
       return apolloClient
           .query(CountryQuery(code))
           .execute()
           .data
              ?.country
              ?.toDetailedCountry()
    }
}