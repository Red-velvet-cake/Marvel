package com.red_velvet.marvel.ui.comics

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel

class ComicsViewModel : BaseViewModel(), ComicsInteractionListener {
    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

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

    private val _thisWeekComics = MutableLiveData<State<List<ComicsResponse>>>(State.Loading)
    val thisWeekComicsLiveData: LiveData<State<List<ComicsResponse>>> = _thisWeekComics

    private val _nextWeekComics = MutableLiveData<State<List<ComicsResponse>>>(State.Loading)

    private val _lastWeekComics = MutableLiveData<State<List<ComicsResponse>>>(State.Loading)

    private val _thisMonthComics = MutableLiveData<State<List<ComicsResponse>>>(State.Loading)

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
            ::handleThisWeekComicsSuccess
        )
    }

    private fun handleThisWeekComicsFailure(throwable: Throwable) {
        _thisWeekComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleThisWeekComicsSuccess(state: State<List<ComicsResponse>?>) {
        _thisWeekComics.postValue(State.Loading)
        state.toData()?.let {
            _thisWeekComics.postValue(State.Success(it))
            insertNewComicsCollection(thisWeekStringResource, state)
        }
    }

    private fun getNextWeekComics() {
        bindStateUpdates(
            repository.getComics(dateDescriptor = NEXT_WEEK),
            ::handleNextWeekComicsFailure,
            ::handleNextWeekComicsSuccess
        )
    }

    private fun handleNextWeekComicsFailure(throwable: Throwable) {
        _nextWeekComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleNextWeekComicsSuccess(state: State<List<ComicsResponse>?>) {
        _nextWeekComics.postValue(State.Loading)
        state.toData()?.let {
            _nextWeekComics.postValue(State.Success(it))
            insertNewComicsCollection(nextWeekStringResource, state)
        }
    }

    private fun getLastWeekComics() {
        bindStateUpdates(
            repository.getComics(dateDescriptor = LAST_WEEK),
            ::handleLastWeekComicsFailure,
            ::handleLastWeekComicsSuccess
        )
    }

    private fun handleLastWeekComicsFailure(throwable: Throwable) {
        _lastWeekComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleLastWeekComicsSuccess(state: State<List<ComicsResponse>?>) {
        _lastWeekComics.postValue(State.Loading)
        state.toData()?.let {
            _lastWeekComics.postValue(State.Success(it))
            insertNewComicsCollection(lastWeekStringResource, state)
        }
    }

    private fun getThisMonthComics() {
        bindStateUpdates(
            repository.getComics(dateDescriptor = THIS_MONTH),
            ::handleThisMonthComicsFailure,
            ::handleThisMonthComicsSuccess
        )
    }

    private fun handleThisMonthComicsFailure(throwable: Throwable) {
        _thisMonthComics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleThisMonthComicsSuccess(state: State<List<ComicsResponse>?>) {
        _thisMonthComics.postValue(State.Loading)
        state.toData()?.let {
            _thisMonthComics.postValue(State.Success(it))
            insertNewComicsCollection(thisMonthStringResource, state)
        }
    }

    private fun insertNewComicsCollection(titleId: Int, comics: State<List<ComicsResponse>?>) {
        _comicsCollections.value =
            _comicsCollections.value!!.plus(ComicsCollection(titleId, comics))
    }

    override fun onComicClicked(comic: ComicsResponse) {}

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