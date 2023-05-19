package com.red_velvet.marvel.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("SERIES_SEARCH_TABLE")
data class SeriesSearch(
    @PrimaryKey val id :Int,
    val title :String,
    val img :String,
)
