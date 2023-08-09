package com.example.countryinfo.di

import com.apollographql.apollo3.ApolloClient
import com.example.countryinfo.data.remote.repository.ApolloCountryClient
import com.example.countryinfo.domain.repositiry.CountryClient
import com.example.countryinfo.domain.usecase.GetCountriesUseCase
import com.example.countryinfo.domain.usecase.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCountryUseCase(countryClient: CountryClient): GetCountryUseCase {
        return GetCountryUseCase(countryClient)
    }

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(countryClient : CountryClient): GetCountriesUseCase {
        return GetCountriesUseCase(countryClient)
    }
}