package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.local.entity.EventEntity
import com.red_velvet.marvel.domain.model.Event

class EventMapper : Mapper<EventEntity , Event> {
    override fun map(input: EventEntity): Event {
        return Event(
            id = input.id ?: 0L ,
            name = input.name ?: "" ,
            imageUrl = input.imageUrl ?: ""
        )
    }
}