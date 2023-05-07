package com.red_velvet.marvel.ui.utils

sealed class State<out T> {
    data class Success<T>(val data: T) : State<T>()
    data class Failed(val error: String) : State<Nothing>()
    object Loading : State<Nothing>()

    fun toData(): T? = if (this is Success) data else null
}
