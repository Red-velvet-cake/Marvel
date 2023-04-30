package com.red_velvet.marvel.ui.home

import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel() {
    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: MutableLiveData<String> get() = _message

    init {
        getData()
    }

    override fun getData() {
        _message.postValue("Hello From Sadeq")
    }
}