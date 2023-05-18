package com.red_velvet.marvel.ui.seriesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.remote.dto.CreatorDto
import com.red_velvet.marvel.data.remote.dto.SeriesDto
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.State

class SeriesDetailsViewModel : BaseViewModel(), CreatorListenerInteraction {

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private val _series: MutableLiveData<State<List<SeriesDto>>> = MutableLiveData()
    val series: LiveData<State<List<SeriesDto>>> = _series

    private val _creators: MutableLiveData<State<List<CreatorDto>>> = MutableLiveData()
    val creators: LiveData<State<List<CreatorDto>>> = _creators

    fun loadSeriesDetails(seriesId: Int) {
        getSeries(seriesId)
        getCreators(seriesId)
    }

    private fun getSeries(seriesId: Int) {
        bindStateUpdates(
            repository.getSeriesById(seriesId),
            onNext = ::onGetSeriesDetailsSuccess,
            onError = ::onGetSeriesDetailsError
        )
    }

    private fun getCreators(seriesId: Int) {
        bindStateUpdates(
            repository.getCreatorsBySeriesId(seriesId),
            onNext = ::onGetCreatorsBySeriesIdSuccess,
            onError = ::onGetCreatorsBySeriesDetailsError
        )
    }

    private fun onGetSeriesDetailsSuccess(state: State<List<SeriesDto>>) {
        _series.postValue(state)
    }

    private fun onGetSeriesDetailsError(error: Throwable) {
        _series.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetCreatorsBySeriesIdSuccess(state: State<List<CreatorDto>>) {
        _creators.postValue(state)
    }

    private fun onGetCreatorsBySeriesDetailsError(error: Throwable) {
        _creators.postValue(State.Failed(error.message.toString()))
    }
}