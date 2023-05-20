package com.red_velvet.marvel.ui.seriesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.remote.dtos.SeriesDto
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    BaseViewModel(), CreatorListenerInteraction {

    private val _seriesDto: MutableLiveData<State<List<SeriesDto>>> = MutableLiveData()
    val seriesDto: LiveData<State<List<SeriesDto>>> = _seriesDto

    private val _creators: MutableLiveData<State<List<Creator>>> = MutableLiveData()
    val creators: LiveData<State<List<Creator>>> = _creators

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
        _seriesDto.postValue(state)
    }

    private fun onGetSeriesDetailsError(error: Throwable) {
        _seriesDto.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetCreatorsBySeriesIdSuccess(state: State<List<Creator>>) {
        _creators.postValue(state)
    }

    private fun onGetCreatorsBySeriesDetailsError(error: Throwable) {
        _creators.postValue(State.Failed(error.message.toString()))
    }
}