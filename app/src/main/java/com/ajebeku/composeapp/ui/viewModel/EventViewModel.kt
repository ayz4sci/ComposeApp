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
        val city = _uiState.value.searchByCity
        val price = _uiState.value.searchByPrice

        var events = concertRepository.fetchConcerts().extractEvents()
        if (city.isNotBlank()) {
            events = events.filter { it.city?.contains(city, true) == true }
        }
        if (price.isNotBlank()) {
            events = events.filter { price.isNotBlank() && it.price == (price.toIntOrNull() ?: 0) }
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