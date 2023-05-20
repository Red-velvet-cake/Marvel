package com.red_velvet.marvel.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("EVENTS_SEARCH_TABLE")
data class EventsSearch(
    @PrimaryKey val id :Int,
    val title :String,
    val img :String,
)
