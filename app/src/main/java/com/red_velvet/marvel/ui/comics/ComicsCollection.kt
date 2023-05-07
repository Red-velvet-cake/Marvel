package com.red_velvet.marvel.ui.comics

import androidx.annotation.StringRes
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.ui.utils.State

data class ComicsCollection(
    @StringRes val titleId: Int,
    val comics: State<List<Comic>?>
)