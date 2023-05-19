package com.red_velvet.marvel.ui.comics

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.Comics
import com.red_velvet.marvel.data.database.ComicsDataBase
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.repository.MovieRepository
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ComicsViewModel : BaseViewModel(), ComicsInteractionListener {

    val movieRepository=  MovieRepository()

    fun addCashedComics(){
        movieRepository.
        insertData(Comics(0,"aya","img"))
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
    fun getCashedComics(){

        movieRepository.
        getComics()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ::onGetComics,
                ::onGetThisWeekComicsFailure,

                )
    }
    private val repository: MarvelRepository by lazy {
        MarvelRepositoryImpl(RetrofitClient.apiService)
    }

    private val _navigationToComicDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToComicDetails: LiveData<SingleEvent<Int>> = _navigationToComicDetails

    @StringRes
    private val thisWeekStringResource = R.string.this_week

    @StringRes
    private val nextWeekStringResource = R.string.next_week

    @StringRes
    private val lastWeekStringResource = R.string.last_week

    @StringRes
    private val thisMonthStringResource = R.string.this_month

    private val _comicsCollections: MutableLiveData<List<ComicsCollection>> =
        MutableLiveData(emptyList())
    val comicCollections: LiveData<List<ComicsCollection>> = _comicsCollections

    private val _thisWeekComics = MutableLiveData<State<List<Comic>>>(State.Loading)
    val thisWeekComicsLiveData: LiveData<State<List<Comic>>> = _thisWeekComics

    private val _nextWeekComics = MutableLiveData<State<List<Comic>>>(State.Loading)
    private val _comics = MutableLiveData<List<Comic>>()
     val comics : LiveData<List<Comic>> = _comics



    private val _lastWeekComics = MutableLiveData<State<List<Comic>>>(State.Loading)

    private val _thisMonthComics = MutableLiveData<State<List<Comic>>>(State.Loading)

    init {
        getThisWeekComics()
        getNextWeekComics()
        getLastWeekComics()
        getThisMonthComics()
    }

    fun getThisWeekComics() {
        bindStateUpdates(
            repository.getAllComics(dateDescriptor = THIS_WEEK),
            onError = ::onGetThisWeekComicsFailure,
            onNext = ::onGetThisWeekComicsState
        )
    }

    private fun onGetThisWeekComicsFailure(throwable: Throwable) {
        _thisWeekComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun onGetThisWeekComicsState(state: State<List<Comic>>) {
        _thisWeekComics.postValue(state)
        if (state is State.Success) insertNewComicsCollection(thisWeekStringResource, state)
    }
    private fun onGetComics(comics: List<Comic>) {
        _comics.postValue(comics)

    }

    fun getNextWeekComics() {
        bindStateUpdates(
            repository.getAllComics(dateDescriptor = NEXT_WEEK),
            onError = ::onGetNextWeekComicsFailure,
            onNext = ::onGetNextWeekComicsState
        )
    }

    private fun onGetNextWeekComicsFailure(throwable: Throwable) {
        _nextWeekComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun onGetNextWeekComicsState(state: State<List<Comic>>) {
        _nextWeekComics.postValue(state)
        if (state is State.Success) insertNewComicsCollection(nextWeekStringResource, state)
    }

    fun getLastWeekComics() {
        bindStateUpdates(
            repository.getAllComics(dateDescriptor = LAST_WEEK),
            onError = ::onGetLastWeekComicsFailure,
            onNext = ::onGetLastWeekComicsState
        )
    }

    private fun onGetLastWeekComicsFailure(throwable: Throwable) {
        _lastWeekComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun onGetLastWeekComicsState(state: State<List<Comic>>) {
        _lastWeekComics.postValue(state)
        if (state is State.Success) insertNewComicsCollection(lastWeekStringResource, state)
    }

    fun getThisMonthComics() {
        bindStateUpdates(
            repository.getAllComics(dateDescriptor = THIS_MONTH),
            onError = ::onGetThisMonthComicsFailure,
            onNext = ::onGetThisMonthComicsState
        )
    }

    private fun onGetThisMonthComicsFailure(throwable: Throwable) {
        _thisMonthComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun onGetThisMonthComicsState(state: State<List<Comic>>) {
        _thisMonthComics.postValue(state)
        if (state is State.Success) insertNewComicsCollection(thisMonthStringResource, state)
    }

    private fun insertNewComicsCollection(titleId: Int, comics: State<List<Comic>>) {
        _comicsCollections.value =
            _comicsCollections.value!!.plus(ComicsCollection(titleId, comics))
    }

    override fun doOnComicClicked(comicId: Int) {
        _navigationToComicDetails.postValue(SingleEvent(comicId))
    }

    companion object {
        private const val THIS_WEEK = "thisWeek"
        private const val NEXT_WEEK = "nextWeek"
        private const val LAST_WEEK = "lastWeek"
        private const val THIS_MONTH = "thisMonth"
        private const val UNKNOWN_ERROR = "Unknown error"
    }
}