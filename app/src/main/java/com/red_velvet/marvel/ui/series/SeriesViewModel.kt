package com.red_velvet.marvel.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.MarvelRepository
import com.red_velvet.marvel.ui.base.BaseViewModel


class SeriesViewModel : BaseViewModel() {

    private val _series: MutableLiveData<State<List<SeriesResponse>>> = MutableLiveData()
    val series: LiveData<State<List<SeriesResponse>>> get() = _series

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    init {
        getSeries()
    }

    private fun getSeries() {
       getData(_series,repository.getAllSeries())
    }

}