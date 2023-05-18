package com.red_velvet.marvel.data.local.database.entity

import androidx.room.Entity

@Entity(tableName = "THUMBNAIL_TABLE")
data class ThumbnailEntity(
    val extension: String,
    val path: String,
)
