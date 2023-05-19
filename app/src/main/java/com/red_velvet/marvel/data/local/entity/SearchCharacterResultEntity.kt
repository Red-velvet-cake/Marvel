package com.red_velvet.marvel.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchCharacterResult_table")
data class SearchCharacterResultEntity(
    @PrimaryKey var id: Long,
    var name: String,
    var imgUrl: String)