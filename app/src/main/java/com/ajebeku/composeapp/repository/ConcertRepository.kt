package com.ajebeku.composeapp.repository

import android.content.res.AssetManager
import com.ajebeku.composeapp.models.Concert
import com.google.gson.Gson

interface ConcertRepositoryApi {
    fun fetchConcerts(): Concert
}

class ConcertRepository(private val assetManager: AssetManager) : ConcertRepositoryApi {
    override fun fetchConcerts(): Concert {
        val gson = Gson()
        return gson.fromJson(
            assetManager.open("sampleData.json").bufferedReader(),
            Concert::class.java
        )
    }
}