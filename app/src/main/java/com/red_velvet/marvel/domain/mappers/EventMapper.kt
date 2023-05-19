package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.model.ComicDto
import com.red_velvet.marvel.data.model.EventDto
import com.red_velvet.marvel.domain.models.Comic
import com.red_velvet.marvel.domain.models.Event
import javax.inject.Inject

class EventMapper  @Inject constructor() : Mapper<EventDto, Event> {
    override fun map(input: EventDto): Event {
        return  Event(
            id =input.id, title = input.title,
            image = "${input.thumbnail?.path}.${input.thumbnail?.extension}" )
    }
}