package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.local.entity.CharacterEntity
import com.red_velvet.marvel.domain.model.Character

class CharacterMapper : Mapper<CharacterEntity , Character> {
    override fun map(input: CharacterEntity): Character {
        return Character(
            id = input.id ?: 0L ,
            name = input.name ?: "" ,
            imageUrl = input.imageUrl ?: ""
        )
    }
}