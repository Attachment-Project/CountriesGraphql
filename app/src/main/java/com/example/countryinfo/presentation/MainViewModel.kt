package com.example.countryinfo.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.countryinfo.domain.model.SimpleCountry
import com.example.countryinfo.domain.usecase.GetCountryUseCase
import com.example.countryinfo.domain.usecase.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCountriesUseCase : GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
): ViewModel() {
    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                )
            }
            val countries = getCountriesUseCase.execute()
            try {
                countries?.let {
                        countryList ->
                    _state.update {
                        it.copy(
                            countries = countryList,
                            isLoading = false
                        )
                    }
                }
            } catch (e: ApolloException) {
                _state.update {
                    it.copy(
                        isError = true,
                        isLoading = false
                    )
                }
            }
            catch (e: Exception) {
                _state.update {
                    it.copy(
                        isError = true,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun selectCountry(code: String){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                )
            }
            _state.update {
                it.copy(
                    selectedCountry = getCountryUseCase.execute(code),
                    isLoading = false
                )
            }
        }
    }

    fun dismissSelectedCountry(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = null
                )
            }
        }
    }

}