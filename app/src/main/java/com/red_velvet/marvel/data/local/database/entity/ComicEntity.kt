package com.red_velvet.marvel.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COMIC_TABLE")
data class ComicEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val thumbnail: ThumbnailEntity,
)