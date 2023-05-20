package com.red_velvet.marvel.domain.mappers

import android.provider.CalendarContract.EventsEntity
import com.red_velvet.marvel.data.entity.ComicsEntity
import com.red_velvet.marvel.data.model.ComicDto
import com.red_velvet.marvel.data.model.EventDto
import com.red_velvet.marvel.domain.models.Comic
import com.red_velvet.marvel.domain.models.Event
import javax.inject.Inject

class EventMapper @Inject constructor() : Mapper<EventsEntity, Event> {
    override fun map(input: EventsEntity): Event {
        return  Event(
            id =input.id, title = input.title,
            image = input.img )
    }
}