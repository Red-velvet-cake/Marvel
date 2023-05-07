package com.red_velvet.marvel.ui.comics

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State

class ComicsViewModel : BaseViewModel(), ComicsInteractionListener {
    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

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

    private val _lastWeekComics = MutableLiveData<State<List<Comic>>>(State.Loading)

    private val _thisMonthComics = MutableLiveData<State<List<Comic>>>(State.Loading)

    init {
        getThisWeekComics()
        getNextWeekComics()
        getLastWeekComics()
        getThisMonthComics()
    }

    private fun getThisWeekComics() {
        bindStateUpdates(
            repository.getComics(dateDescriptor = THIS_WEEK),
            ::handleThisWeekComicsFailure,
            ::handleThisWeekComicsNextState
        )
    }

    private fun handleThisWeekComicsFailure(throwable: Throwable) {
        _thisWeekComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleThisWeekComicsNextState(state: State<List<Comic>>) {
        _thisWeekComics.postValue(state)
        if (state is State.Success) insertNewComicsCollection(thisWeekStringResource, state)
    }

    private fun getNextWeekComics() {
        bindStateUpdates(
            repository.getComics(dateDescriptor = NEXT_WEEK),
            ::handleNextWeekComicsFailure,
            ::handleNextWeekComicsNextState
        )
    }

    private fun handleNextWeekComicsFailure(throwable: Throwable) {
        _nextWeekComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleNextWeekComicsNextState(state: State<List<Comic>>) {
        _nextWeekComics.postValue(state)
        if (state is State.Success) insertNewComicsCollection(nextWeekStringResource, state)
    }

    private fun getLastWeekComics() {
        bindStateUpdates(
            repository.getComics(dateDescriptor = LAST_WEEK),
            ::handleLastWeekComicsFailure,
            ::handleLastWeekComicsNextState
        )
    }

    private fun handleLastWeekComicsFailure(throwable: Throwable) {
        _lastWeekComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleLastWeekComicsNextState(state: State<List<Comic>>) {
        _lastWeekComics.postValue(state)
        if (state is State.Success) insertNewComicsCollection(lastWeekStringResource, state)
    }

    private fun getThisMonthComics() {
        bindStateUpdates(
            repository.getComics(dateDescriptor = THIS_MONTH),
            ::handleThisMonthComicsFailure,
            ::handleThisMonthComicsNextState
        )
    }

    private fun handleThisMonthComicsFailure(throwable: Throwable) {
        _thisMonthComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleThisMonthComicsNextState(state: State<List<Comic>>) {
        _thisMonthComics.postValue(state)
        if (state is State.Success) insertNewComicsCollection(thisMonthStringResource, state)
    }

    private fun insertNewComicsCollection(titleId: Int, comics: State<List<Comic>?>) {
        _comicsCollections.value =
            _comicsCollections.value!!.plus(ComicsCollection(titleId, comics))
    }

    override fun onComicClicked(comicId: Int) {
        _navigationToComicDetails.postValue(SingleEvent(comicId))
    }

    override fun onTryAgainClicked() {
        getThisWeekComics()
        getNextWeekComics()
        getLastWeekComics()
        getThisMonthComics()
    }

    companion object {
        private const val THIS_WEEK = "thisWeek"
        private const val NEXT_WEEK = "nextWeek"
        private const val LAST_WEEK = "lastWeek"
        private const val THIS_MONTH = "thisMonth"
        private const val UNKNOWN_ERROR = "Unknown error"
    }
}