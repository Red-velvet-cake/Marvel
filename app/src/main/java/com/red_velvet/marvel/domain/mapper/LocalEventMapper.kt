package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.local.database.entity.EventEntity
import com.red_velvet.marvel.domain.model.Event

class LocalEventMapper : Mapper<EventEntity, Event> {
    override fun map(input: EventEntity): Event {
        return Event(
            id = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
        )
    }
}