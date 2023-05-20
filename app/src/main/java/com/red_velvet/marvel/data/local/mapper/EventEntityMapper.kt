package com.red_velvet.marvel.data.local.mapper

import com.red_velvet.marvel.data.local.database.entity.EventEntity
import com.red_velvet.marvel.data.remote.dto.EventDto
import com.red_velvet.marvel.domain.mapper.Mapper
import com.red_velvet.marvel.domain.util.toUrl

class EventEntityMapper : Mapper<EventDto, EventEntity> {
    override fun map(input: EventDto): EventEntity {
        return EventEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = input.thumbnail?.toUrl() ?: "",
        )
    }
}