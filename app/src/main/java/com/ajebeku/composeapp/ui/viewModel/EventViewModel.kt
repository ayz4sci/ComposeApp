package com.ajebeku.composeapp.ui.viewModel

import com.ajebeku.composeapp.repository.ConcertRepositoryApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class EventViewModel(repositoryApi: ConcertRepositoryApi) {
    private val uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: Flow<UiState> = uiState.asStateFlow()

    fun fetchEvents(name: String? = null, city: String? = null) {
    }

    data class UiState(val events: ArrayList<String> = arrayListOf())
}