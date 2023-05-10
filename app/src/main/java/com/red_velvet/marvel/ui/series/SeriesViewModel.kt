package com.red_velvet.marvel.ui.series

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Series
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit


class SeriesViewModel : BaseViewModel(), SeriesInteractionListener {

    private val _navigationToSeriesDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToSeriesDetails: LiveData<SingleEvent<Int>> = _navigationToSeriesDetails

    private val _series: MutableLiveData<State<List<Series>>> = MutableLiveData()
    val series: LiveData<State<List<Series>>> = _series

    val repository: MarvelRepository by lazy { MarvelRepositoryImpl(RetrofitClient.apiService) }

    val searchQuery = MutableLiveData<String>()

    init {
        getAllSeries()
        searchResult()
    }

    private fun search(query: String) {
        bindStateUpdates(
            repository.getAllSeries(contains = query),
            ::onError,
            ::onSuccess
        )
    }

    fun getAllSeries() {
        bindStateUpdates(repository.getAllSeries(), ::onError, ::onSuccess)
    }

    private fun onError(error: Throwable) {
        _series.postValue(State.Failed(error.message.toString()))
    }

    private fun onSuccess(state: State<List<Series>>) {
        _series.postValue(state)
    }

    fun filterSeries(filter: String) {
        bindStateUpdates(repository.getAllSeries(contains = filter), ::onError, ::onSuccess)
    }

    private fun searchResult() {
        Observable.create { emitter ->
            searchQuery.observeForever { query ->
                emitter.onNext(query)
            }
        }.debounce(1, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { query ->

                if (query.isEmpty()) {

                    getAllSeries()
                } else {
                    Log.d("ethaar", query)
                    search(query)
                }
            }.addTo(compositeDisposable)
    }

    override fun onSeriesClicked(seriesId: Int) {
        _navigationToSeriesDetails.postValue(SingleEvent(seriesId))
    }

}


