package com.red_velvet.marvel.domain.mapper.comics

import com.red_velvet.marvel.data.local.entity.ComicsEntity
import com.red_velvet.marvel.domain.mapper.Mapper
import com.red_velvet.marvel.domain.model.Comics

class ComicsEntityToComicsMapper : Mapper<ComicsEntity, Comics> {

    override fun map(input: ComicsEntity, dateDescriptor: String?): Comics {
          return Comics(
            id = input.id.toInt(),
            title = input.title,
            dateDescriptor = dateDescriptor ?: "",
            imgUrl = input.imgUrl
        )
    }
}