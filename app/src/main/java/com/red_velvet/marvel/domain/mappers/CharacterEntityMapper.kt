package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.local.entity.CharacterEntity
import com.red_velvet.marvel.data.remote.dtos.CharacterDto
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor() : Mapper<CharacterDto, CharacterEntity> {
    override fun map(input: CharacterDto): CharacterEntity {
        return CharacterEntity(
            id = input.id ?: 0,
            title = input.name ?: "",
            description = input.description ?: "",
            modified = input.modified ?: "",
            imageUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}