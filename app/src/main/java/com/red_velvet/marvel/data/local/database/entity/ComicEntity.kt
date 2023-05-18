package com.red_velvet.marvel.data.local.database.entity

import androidx.room.PrimaryKey

data class ComicEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val thumbnail: ThumbnailEntity,
)
