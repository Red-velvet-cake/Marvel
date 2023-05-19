package com.red_velvet.marvel.domain.mapper.comics

import com.red_velvet.marvel.data.local.entity.ComicsEntity
import com.red_velvet.marvel.data.model.ComicDto
import com.red_velvet.marvel.domain.mapper.Mapper

class ComicDtoToComicsEntityMapper : Mapper<ComicDto, ComicsEntity> {

    override fun map(input: ComicDto, dateDescriptor: String?): ComicsEntity {
        return ComicsEntity(
            id = input.id?.toLong() ?: 0L,
            title = input.title.orEmpty(),
            dateDescriptor = dateDescriptor ?: "",
            imgUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}