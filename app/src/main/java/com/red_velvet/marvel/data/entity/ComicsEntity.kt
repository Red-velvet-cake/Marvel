package com.red_velvet.marvel.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("COMICS_TABLE")
data class ComicsEntity(
    @PrimaryKey val id :Int,
    val title :String,
    val img :String,
)
