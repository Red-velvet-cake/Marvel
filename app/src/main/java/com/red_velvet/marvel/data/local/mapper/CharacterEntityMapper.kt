package com.red_velvet.marvel.data.local.mapper

import com.red_velvet.marvel.data.local.database.entity.CharacterEntity
import com.red_velvet.marvel.data.remote.dto.CharacterDto
import com.red_velvet.marvel.domain.mapper.Mapper
import com.red_velvet.marvel.domain.util.toUrl

class CharacterEntityMapper : Mapper<CharacterDto, CharacterEntity> {
    override fun map(input: CharacterDto): CharacterEntity {
        return CharacterEntity(
            id = input.id ?: 0,
            name = input.name ?: "",
            imageUrl = input.thumbnail?.toUrl() ?: ""
        )
    }
}