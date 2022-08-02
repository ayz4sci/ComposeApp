package com.ajebeku.composeapp.useCases

import com.ajebeku.composeapp.models.Concerts
import com.ajebeku.composeapp.models.Event
import com.ajebeku.composeapp.repository.ConcertRepositoryApi

class EventsUseCase(private val concertRepositoryApi: ConcertRepositoryApi) {
    fun invoke(): ArrayList<Event> {
        val events = arrayListOf<Event>()
        val concerts = concertRepositoryApi.fetchConcerts()
        events.addAll(concerts.events)
        events.addAll(fetchAllEvents(concerts.children))

        return events
    }

    private fun fetchAllEvents(children: ArrayList<Concerts>): ArrayList<Event> {
        val events = arrayListOf<Event>()
        children.forEach {
            events.addAll(it.events)
            events.addAll(fetchAllEvents(it.children))
        }

        return events
    }
}