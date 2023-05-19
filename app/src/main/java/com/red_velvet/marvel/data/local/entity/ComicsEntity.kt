package com.red_velvet.marvel.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Comics_table")
data class ComicsEntity (
    @PrimaryKey val id: Long,
    val title: String,
    val imgUrl: String,
    val dateDescriptor: String
)