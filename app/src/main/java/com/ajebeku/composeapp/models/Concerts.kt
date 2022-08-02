package com.ajebeku.composeapp.models

import com.google.gson.annotations.SerializedName


data class Concerts(
    val id: Int,
    val name: String? = null,
    @SerializedName("children") val children: ArrayList<Concerts> = arrayListOf(),
    val events: ArrayList<Event> = arrayListOf()
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