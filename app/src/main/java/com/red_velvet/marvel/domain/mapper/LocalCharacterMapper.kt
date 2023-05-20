package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.local.database.entity.CharacterEntity
import com.red_velvet.marvel.domain.model.Character

class LocalCharacterMapper : Mapper<CharacterEntity, Character> {
    override fun map(input: CharacterEntity): Character {
        return Character(
            id = input.id,
            name = input.name,
            imageUrl = input.imageUrl,
        )
    }
}