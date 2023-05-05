package com.red_velvet.marvel.ui.comics

import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.util.State

data class ComicsCollection(
    val title: String,
    val comics: State<List<ComicsResponse>>
)