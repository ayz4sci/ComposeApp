package com.ajebeku.composeapp.ui.viewModel

import com.ajebeku.composeapp.models.Concert
import com.ajebeku.composeapp.models.Event

fun Concert.extractEvents(): List<Event> {
    val events = arrayListOf<Event>()
    events.addAll(events)
    events.addAll(fetchAllEvents(children))

    return events
}

private fun fetchAllEvents(children: List<Concert>): List<Event> {
    val events = arrayListOf<Event>()
    children.forEach {
        events.addAll(it.events)
        events.addAll(fetchAllEvents(it.children))
    }

    return events
}