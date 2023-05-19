package com.red_velvet.marvel.ui.series

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Series
import com.red_velvet.marvel.domain.repository.MarvelRepository
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), SeriesInteractionListener {

    private val _navigationToSeriesDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToSeriesDetails: LiveData<SingleEvent<Int>> = _navigationToSeriesDetails

    private val _series: MutableLiveData<State<List<Series>>> = MutableLiveData()
    val series: LiveData<State<List<Series>>> = _series

    val searchQuery = MutableLiveData<String>()

    init {
        initSearchObservable()
        getAllSeries()
    }

    fun getAllSeries(titleStartsWith: String? = null, contains: String? = null) {
        bindStateUpdates(
            repository.getAllSeries(titleStartsWith, contains),
            ::onGetSeriesFailure,
            ::onGetSeriesState
        )
    }

    private fun onGetSeriesFailure(error: Throwable) {
        _series.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetSeriesState(state: State<List<Series>>) {
        _series.postValue(state)
    }

    private fun initSearchObservable() {
        Observable.create { emitter ->
            searchQuery.observeForever { query ->
                emitter.onNext(query)
            }
        }.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { query ->
                if (query.isEmpty()) {
                    getAllSeries()
                } else {
                    Log.d("thio", query)
                    getAllSeries(query)
                }
            }.addTo(compositeDisposable)
    }

    override fun doOnSeriesClicked(seriesId: Int) {
        _navigationToSeriesDetails.postValue(SingleEvent(seriesId))
    }

}