package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.local.entity.EventEntity
import com.red_velvet.marvel.data.remote.dtos.EventDto
import com.red_velvet.marvel.domain.model.Event

class EventEntityMapper : Mapper<EventDto, EventEntity> {
    override fun map(input: EventDto): EventEntity {
        return EventEntity(
            id = input.id?.toLong() ?: 0L ,
            name = input.title ?: "" ,
            description = input.description ?: "" ,
            modified = input.modified ?: "" ,
            imageUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}