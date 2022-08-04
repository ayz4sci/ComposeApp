package com.ajebeku.composeapp

import android.app.Application
import com.ajebeku.composeapp.repository.ConcertRepository

class ComposeApplication : Application() {
    val concertRepositoryApi by lazy {
        ConcertRepository(applicationContext.assets)
    }
}