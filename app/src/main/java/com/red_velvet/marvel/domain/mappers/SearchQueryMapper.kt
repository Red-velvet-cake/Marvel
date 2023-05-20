package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.local.entity.SearchQueryEntity
import com.red_velvet.marvel.domain.models.SearchQuery
import javax.inject.Inject

class SearchQueryMapper @Inject constructor() : Mapper<SearchQueryEntity, SearchQuery> {
    override fun map(input: SearchQueryEntity): SearchQuery {
        return SearchQuery(
            id = input.id,
            query = input.query
        )
    }
}