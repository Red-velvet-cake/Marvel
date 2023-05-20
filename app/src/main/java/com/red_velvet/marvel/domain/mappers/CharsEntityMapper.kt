package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.entity.CharsEntity
import com.red_velvet.marvel.data.entity.ComicsEntity
import com.red_velvet.marvel.data.model.CharacterDto
import com.red_velvet.marvel.data.model.ComicDto
import javax.inject.Inject

class CharsEntityMapper @Inject constructor() : Mapper<CharacterDto, CharsEntity> {
    override fun map(input: CharacterDto): CharsEntity {
        return CharsEntity(
            id = input.id ?: 0,
            title = input.name ?: "",
            img = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}