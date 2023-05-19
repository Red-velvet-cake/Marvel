package com.red_velvet.marvel.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Events_TABLE")
data class Events(
    @PrimaryKey val id :Int,
    val title :String,
    val img :String,
)
