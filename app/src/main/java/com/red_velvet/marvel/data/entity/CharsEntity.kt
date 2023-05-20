package com.red_velvet.marvel.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CHARS_TABLE")
data class CharsEntity(
    @PrimaryKey val id :Int,
    val title :String,
    val img :String,
)
