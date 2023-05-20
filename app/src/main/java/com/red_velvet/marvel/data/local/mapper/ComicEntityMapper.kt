package com.red_velvet.marvel.data.local.mapper

import com.red_velvet.marvel.data.local.database.entity.ComicEntity
import com.red_velvet.marvel.data.remote.dto.ComicDto
import com.red_velvet.marvel.domain.mapper.Mapper
import com.red_velvet.marvel.domain.util.toUrl

class ComicEntityMapper : Mapper<ComicDto, ComicEntity> {
    override fun map(input: ComicDto): ComicEntity {
        return ComicEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = input.thumbnail?.toUrl() ?: "",
        )
    }
}