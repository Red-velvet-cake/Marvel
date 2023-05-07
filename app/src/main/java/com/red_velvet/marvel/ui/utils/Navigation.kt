package com.red_velvet.marvel.ui.utils

import androidx.navigation.NavDirections

sealed class Navigation {
    class Direction(val direction: NavDirections) : Navigation()
    object Back : Navigation()
}
