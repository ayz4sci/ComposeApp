package com.ajebeku.composeapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajebeku.composeapp.ComposeApplication

class MyViewModelFactory(private val application: ComposeApplication) :
    ViewModelProvider.NewInstanceFactory() {
    private val concertRepositoryApi = application.concertRepositoryApi

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
            return EventViewModel(concertRepositoryApi) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}