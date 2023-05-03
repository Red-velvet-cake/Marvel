package com.red_velvet.marvel.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.MarvelRepository
import com.red_velvet.marvel.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class SeriesViewModel : BaseViewModel() {
    private val _series: MutableLiveData<State<List<SeriesResponse>>> = MutableLiveData()

    val series: LiveData<State<List<SeriesResponse>>> get() = _series

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    init {
        getSeries()
    }

    private fun getSeries() {
        _series.postValue(State.Loading)
        try {
            repository.getAllSeries()
                .doOnError(::onGetSeriesError)
                .doOnSuccess(::onGetSeriesSuccess)
                .subscribeBy(
                    onError = ::onGetSeriesError,
                    onSuccess = ::onGetSeriesSuccess
                ).addTo(compositeDisposable)
        } catch (e: Exception) {
            onGetSeriesError(e)
        }

    }

    private fun onGetSeriesError(error: Throwable) {
        _series.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetSeriesSuccess(response: BaseResponse<SeriesResponse>) {
        _series.postValue(State.Success(response.data?.results!!))
    }
}