package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.local.entity.EventEntity
import com.red_velvet.marvel.data.remote.dtos.EventDto
import javax.inject.Inject

class EventEntityMapper @Inject constructor() : Mapper<EventDto, EventEntity> {
    override fun map(input: EventDto): EventEntity {
        return EventEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            description = input.description ?: "",
            modified = input.modified ?: "",
            imageUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}

