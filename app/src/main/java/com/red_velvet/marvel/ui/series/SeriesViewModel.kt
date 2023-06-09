package com.red_velvet.marvel.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.domain.models.SearchQuery
import com.red_velvet.marvel.domain.models.Series
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.SearchInteractionListener
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class SeriesViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    BaseViewModel(), SeriesInteractionListener, SearchInteractionListener {

    private val _navigationToSeriesDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToSeriesDetails: LiveData<SingleEvent<Int>> = _navigationToSeriesDetails

    private val _series: MutableLiveData<List<Series>> = MutableLiveData()
    val seriesLiveData: LiveData<List<Series>> = _series

    val searchQuery = MutableLiveData<String>()

    private val _searchQueries: MutableLiveData<List<SearchQuery>> = MutableLiveData()
    val searchQueries: LiveData<List<SearchQuery>> = _searchQueries

    init {
        initSearchObservable()
        getAllSeries()
        getSearchedQueries()
    }

    fun getAllSeries(titleStartsWith: String = "", contains: String = "") {
        repository.getAllSeries(titleStartsWith, contains)
            .subscribe(
                { if (it.isNotEmpty()) _series.postValue(it) },
                {}
            )
            .addTo(compositeDisposable)
    }

    private fun initSearchObservable() {
        Observable.create { emitter ->
            searchQuery.observeForever { query ->
                emitter.onNext(query)
            }
        }.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { query ->
                repository.insertSearchQuery(query).subscribe()
                getAllSeries(query)
            }.addTo(compositeDisposable)
    }

    private fun getSearchedQueries() {
        repository.getSearchQueries().subscribe { queries ->
            _searchQueries.postValue(queries)
        }.addTo(compositeDisposable)
    }

    override
    fun doOnSeriesClicked(seriesId: Int) {
        _navigationToSeriesDetails.postValue(SingleEvent(seriesId))
    }

    override fun doOnSearchQueryClicked(query: String) {
        searchQuery.postValue(query)
    }

    override fun doOnSearchQueryDeleteClicked(id: Int) {
        repository.deleteSearchQuery(id).subscribe()
    }

}