package com.ajebeku.composeapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.ajebeku.composeapp.models.Event
import com.ajebeku.composeapp.repository.ConcertRepositoryApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventViewModel(private val concertRepository: ConcertRepositoryApi) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        newState(concertRepository.fetchConcerts().extractEvents())
    }

    fun fetchEventsBy() {
        val searchByCity = _uiState.value.searchByCity
        val searchByPrice = _uiState.value.searchByPrice.toIntOrNull()

        var events = concertRepository.fetchConcerts().extractEvents()
        if (searchByCity.isNotBlank()) {
            events = events.filter { it.city?.contains(searchByCity, true) == true }
        }
        searchByPrice?.let {
            events = events.filter { it.price != null && it.price <= searchByPrice }
        }

        newState(events)
    }

    fun onCityTextChanged(city: String) {
        _uiState.value = _uiState.value.copy(searchByCity = city)
    }

    fun onPriceTextChanged(price: String) {
        _uiState.value = _uiState.value.copy(searchByPrice = price)
    }

    private fun newState(events: List<Event>) {
        _uiState.value = _uiState.value.copy(events = events)
    }

    data class UiState(
        val events: List<Event> = arrayListOf(),
        val searchByCity: String = "",
        val searchByPrice: String = "",
    )
}