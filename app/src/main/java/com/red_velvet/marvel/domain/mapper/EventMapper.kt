package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.remote.dto.EventDto
import com.red_velvet.marvel.domain.model.Event
import com.red_velvet.marvel.domain.util.toUrl

class EventMapper : Mapper<EventDto, Event> {
    override fun map(input: EventDto): Event {
        return Event(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = input.thumbnail?.toUrl() ?: "",
        )
    }
}