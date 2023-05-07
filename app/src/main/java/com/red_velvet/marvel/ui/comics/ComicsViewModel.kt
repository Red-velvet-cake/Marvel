package com.red_velvet.marvel.ui.comics

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel
import io.reactivex.rxjava3.core.Observable

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

    private val comicsCollections: MutableLiveData<State<List<ComicsCollection>>> =
        MutableLiveData(State.Loading)
    val comicCollections: LiveData<State<List<ComicsCollection>>> get() = comicsCollections

    init {
        getAllComicsCollections()
    }

    private fun getAllComicsCollections() {
        bindStateUpdates(
            getAllComicsCollectionObservable(),
            ::handleComicsCollectionsFailure,
            ::handleComicsCollectionsSuccess,
        )
    }

    private fun getAllComicsCollectionObservable(): Observable<State<List<ComicsCollection>>> {
        return Observable.zip(
            repository.getComics(dateDescriptor = THIS_WEEK),
            repository.getComics(dateDescriptor = NEXT_WEEK),
            repository.getComics(dateDescriptor = LAST_WEEK),
            repository.getComics(dateDescriptor = THIS_MONTH)
        ) { thisWeek, nextWeek, lastWeek, thisMonth ->
            handleComicsCollectionsState(thisWeek, nextWeek, lastWeek, thisMonth)
        }
    }

    private fun handleComicsCollectionsState(
        thisWeek: State<List<Comic>?>,
        nextWeek: State<List<Comic>?>,
        lastWeek: State<List<Comic>?>,
        thisMonth: State<List<Comic>?>
    ): State<List<ComicsCollection>> {
        return if (areAllComicsCollectionsSuccess(thisWeek, nextWeek, lastWeek, thisMonth)) {
            State.Success(
                listOf(
                    ComicsCollection(thisWeekStringResource, thisWeek),
                    ComicsCollection(nextWeekStringResource, nextWeek),
                    ComicsCollection(lastWeekStringResource, lastWeek),
                    ComicsCollection(thisMonthStringResource, thisMonth)
                )
            )
        } else if (isAnyComicsCollectionLoading(thisWeek, nextWeek, lastWeek, thisMonth)) {
            State.Loading
        } else {
            State.Failed(UNKNOWN_ERROR)
        }
    }

    private fun areAllComicsCollectionsSuccess(
        thisWeek: State<List<Comic>?>,
        nextWeek: State<List<Comic>?>,
        lastWeek: State<List<Comic>?>,
        thisMonth: State<List<Comic>?>
    ): Boolean {
        return thisWeek is State.Success
                && nextWeek is State.Success
                && lastWeek is State.Success
                && thisMonth is State.Success
    }

    private fun isAnyComicsCollectionLoading(
        thisWeek: State<List<Comic>?>,
        nextWeek: State<List<Comic>?>,
        lastWeek: State<List<Comic>?>,
        thisMonth: State<List<Comic>?>
    ): Boolean {
        return thisWeek is State.Loading
                || nextWeek is State.Loading
                || lastWeek is State.Loading
                || thisMonth is State.Loading
    }

    private fun handleComicsCollectionsFailure(throwable: Throwable) {
        comicsCollections.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun handleComicsCollectionsSuccess(state: State<List<ComicsCollection>>) {
        comicsCollections.postValue(state)
    }

    override fun onComicClicked(comic: Comic) {}

    override fun onTryAgainClicked() {
        getAllComicsCollections()
    }


    companion object {
        private const val THIS_WEEK = "thisWeek"
        private const val NEXT_WEEK = "nextWeek"
        private const val LAST_WEEK = "lastWeek"
        private const val THIS_MONTH = "thisMonth"
        private const val UNKNOWN_ERROR = "Unknown error"
    }
}