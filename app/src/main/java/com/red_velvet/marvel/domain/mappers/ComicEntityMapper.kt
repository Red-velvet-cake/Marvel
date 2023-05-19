package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.local.entity.ComicEntity
import com.red_velvet.marvel.data.remote.dtos.ComicDto
import javax.inject.Inject

class ComicEntityMapper @Inject constructor() : Mapper<ComicDto, ComicEntity> {
    override fun map(input: ComicDto): ComicEntity {
        return ComicEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            description = input.description ?: "",
            modified = input.modified ?: "",
            imageUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}