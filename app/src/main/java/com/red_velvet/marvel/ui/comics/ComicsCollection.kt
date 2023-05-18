package com.red_velvet.marvel.ui.comics

import androidx.annotation.StringRes
import com.red_velvet.marvel.data.remote.dto.ComicDto
import com.red_velvet.marvel.ui.utils.State

data class ComicsCollection(
    @StringRes val titleId: Int,
    val comics: State<List<ComicDto>?>
)