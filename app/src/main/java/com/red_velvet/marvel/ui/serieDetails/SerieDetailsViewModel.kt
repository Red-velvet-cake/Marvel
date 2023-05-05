package com.red_velvet.marvel.ui.serieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.serieDetails.adapter.CreatorListenerInteraction

class SerieDetailsViewModel : BaseViewModel(), CreatorListenerInteraction {

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private val _series: MutableLiveData<State<List<SeriesResponse>>> = MutableLiveData()
    val series: LiveData<State<List<SeriesResponse>>> get() = _series

    private val _creators: MutableLiveData<State<List<CreatorsResponse>>> = MutableLiveData()
    val creators: LiveData<State<List<CreatorsResponse>>> get() = _creators


    fun getSerie(id: Int) {
        bindStateUpdates(
            repository.getSeriesDetails(id),
            onNext = ::onGetSeriesDetailsSuccess,
            onError = ::onGetSeriesDetailsError
        )
    }

    fun getCreators(id: Int) {
        bindStateUpdates(
            repository.getSerieCreatorsBySeriesId(id),
            onNext = ::onGetCreatorsBySeriesIdSuccess,
            onError = ::onGetCreatorsBySeriesDetailsError
        )
    }

    private fun onGetSeriesDetailsSuccess(state: State<List<SeriesResponse>?>) {
        _series.postValue(State.Loading)
        state.toData()?.let {
            _series.postValue(State.Success(it))
        }
    }

    private fun onGetSeriesDetailsError(error: Throwable) {
        _series.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetCreatorsBySeriesIdSuccess(state: State<List<CreatorsResponse>?>) {
        _creators.postValue(State.Loading)
        state.toData()?.let {
            _creators.postValue(State.Success(it))
        }
    }

    private fun onGetCreatorsBySeriesDetailsError(error: Throwable) {
        _creators.postValue(State.Failed(error.message.toString()))
    }

    override fun onClickCreator(creator: CreatorsResponse) {
    }
}