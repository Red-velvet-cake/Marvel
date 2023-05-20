package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.entity.ComicsEntity
import com.red_velvet.marvel.data.entity.EventsEntity
import com.red_velvet.marvel.data.model.ComicDto
import com.red_velvet.marvel.data.model.EventDto
import javax.inject.Inject

class EventEntityMapper @Inject constructor() : Mapper<EventDto, EventsEntity> {
    override fun map(input: EventDto): EventsEntity {
        return EventsEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            img = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}