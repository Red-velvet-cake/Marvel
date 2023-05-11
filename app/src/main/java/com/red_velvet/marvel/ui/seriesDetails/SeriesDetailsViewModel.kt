package com.red_velvet.marvel.ui.seriesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.Series
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.State

class SeriesDetailsViewModel : BaseViewModel(), CreatorListenerInteraction {

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private val _series: MutableLiveData<State<List<Series>>> = MutableLiveData()
    val series: LiveData<State<List<Series>>> = _series

    private val _creators: MutableLiveData<State<List<Creator>>> = MutableLiveData()
    val creators: LiveData<State<List<Creator>>> = _creators

    fun loadSeriesDetails(seriesId: Int) {
        getSeries(seriesId)
        getCreators(seriesId)
    }

    private fun getSeries(seriesId: Int) {
        bindStateUpdates(
            repository.getSeriesDetails(seriesId),
            onNext = ::onGetSeriesDetailsSuccess,
            onError = ::onGetSeriesDetailsError
        )
    }

    private fun getCreators(seriesId: Int) {
        bindStateUpdates(
            repository.getSeriesCreatorsBySeriesId(seriesId),
            onNext = ::onGetCreatorsBySeriesIdSuccess,
            onError = ::onGetCreatorsBySeriesDetailsError
        )
    }

    private fun onGetSeriesDetailsSuccess(state: State<List<Series>>) {
        _series.postValue(state)
    }

    private fun onGetSeriesDetailsError(error: Throwable) {
        _series.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetCreatorsBySeriesIdSuccess(state: State<List<Creator>>) {
        _creators.postValue(state)
    }

    private fun onGetCreatorsBySeriesDetailsError(error: Throwable) {
        _creators.postValue(State.Failed(error.message.toString()))
    }

    override fun onClickCreator(creator: Creator) {
    }

}