package com.red_velvet.marvel.ui.serieDetails.adapter

enum class DetailsItemType {
    INFO,
    CREATORS,
    }


data class DetailsItem<T>(
    var item: T,
    var type: DetailsItemType
)