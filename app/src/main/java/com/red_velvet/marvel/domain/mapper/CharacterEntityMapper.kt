package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.local.entity.CharacterEntity
import com.red_velvet.marvel.data.remote.dtos.CharacterDto

class CharacterEntityMapper : Mapper<CharacterDto , CharacterEntity> {
    override fun map(input: CharacterDto): CharacterEntity {
        return CharacterEntity(
            id = input.id?.toLong() ?: 0L ,
            name = input.name ?: "" ,
            description = input.description ?: "" ,
            modified = input.modified ?: "" ,
            imageUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}