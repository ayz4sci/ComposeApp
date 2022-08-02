package com.ajebeku.composeapp.repository

import android.content.res.AssetManager
import com.ajebeku.composeapp.models.Concerts
import com.google.gson.Gson

interface ConcertRepositoryApi {
    fun fetchConcerts(): Concerts
}

class ConcertsRepository(private val assetManager: AssetManager) : ConcertRepositoryApi {
    override fun fetchConcerts(): Concerts {
        val gson = Gson()
        return gson.fromJson(
            assetManager.open("sampleData.json").bufferedReader(),
            Concerts::class.java
        )
    }
}