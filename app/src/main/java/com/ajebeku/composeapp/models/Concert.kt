package com.ajebeku.composeapp.models

import com.google.gson.annotations.SerializedName

data class Concert(
    val id: Int,
    val name: String? = null,
    @SerializedName("children") val children: List<Concert> = arrayListOf(),
    val events: List<Event> = arrayListOf()
)

data class Event(
    val id: Int,
    val name: String? = null,
    val venueName: String? = null,
    val city: String? = null,
    val price: Int? = null,
    val distanceFromVenue: Double? = null,
    val date: String? = null
)