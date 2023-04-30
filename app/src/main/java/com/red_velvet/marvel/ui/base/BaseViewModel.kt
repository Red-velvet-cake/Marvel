package com.red_velvet.marvel.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    abstract fun getData()
}