package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.local.entity.EventEntity
import com.red_velvet.marvel.domain.models.Event
import javax.inject.Inject

class EventMapper @Inject constructor() : Mapper<EventEntity, Event> {
    override fun map(input: EventEntity): Event {
        return Event(
            id = input.id,
            name = input.title,
            imageUrl = input.imageUrl
        )
    }
}