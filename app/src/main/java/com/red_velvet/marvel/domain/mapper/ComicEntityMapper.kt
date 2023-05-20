package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.local.entity.ComicEntity
import com.red_velvet.marvel.data.remote.dtos.ComicDto

class ComicEntityMapper : Mapper<ComicDto,ComicEntity> {
    override fun map(input: ComicDto): ComicEntity {
        return ComicEntity(
            id = input.id?.toLong() ?: 0L ,
            name = input.title ?: "" ,
            description = input.description ?: "" ,
            modified = input.modified ?: "" ,
            imageUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}