package com.red_velvet.marvel.ui.series

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.character.SeriesInteractionListener
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit


class SeriesViewModel : BaseViewModel(), SeriesInteractionListener {

    private val _series: MutableLiveData<State<List<SeriesResponse>>> = MutableLiveData()
    val series: LiveData<State<List<SeriesResponse>>> get() = _series
    val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)
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

    private fun onSuccess(state: State<List<SeriesResponse>?>) {
        _series.postValue(State.Loading)
        state.toData()?.let {
            _series.postValue(State.Success(it))
        }
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

}


