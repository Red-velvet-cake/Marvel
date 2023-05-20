package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.entity.ComicsEntity
import com.red_velvet.marvel.data.model.ComicDto
import javax.inject.Inject

class ComicsEntityMapper @Inject constructor() : Mapper<ComicDto, ComicsEntity> {
    override fun map(input: ComicDto): ComicsEntity {
        return ComicsEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            img = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}