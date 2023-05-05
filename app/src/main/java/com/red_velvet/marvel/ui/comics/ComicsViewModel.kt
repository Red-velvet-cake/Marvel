package com.red_velvet.marvel.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel

class ComicsViewModel : BaseViewModel(), ComicsInteractionListener {
    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private val _comics: MutableLiveData<State<List<ComicsResponse>>> = MutableLiveData()
    val comics: LiveData<State<List<ComicsResponse>>> get() = _comics

    private val _screenItems: MutableLiveData<List<ComicsCollection>> = MutableLiveData()
    val screenItems: LiveData<List<ComicsCollection>> get() = _screenItems


    init {
        getThisWeekComics()
        getScreensItems()
    }

    private fun getThisWeekComics() {
        bindStateUpdates(
            repository.getComics(),
            ::handleComicsError,
            ::handleComicsSuccess
        )

    }

    private fun handleComicsError(throwable: Throwable) {
        _comics.value = State.Failed(throwable.message ?: "Unknown error")
    }

    private fun handleComicsSuccess(state: State<List<ComicsResponse>?>) {
        when (state) {
            is State.Loading -> {
                _comics.postValue(State.Loading)
            }

            is State.Success -> {
                _comics.postValue(State.Success(state.data ?: emptyList()))
            }

            is State.Failed -> {
                _comics.value = State.Failed(state.error)
            }
        }
    }

    private fun getScreensItems() {

        val thisWeek = comics.value?.let {
            ComicsCollection(
                title = "This Week",
                comics = it
            )
        }

        val nextWeek = comics.value?.let {
            ComicsCollection(
                title = "Next Week",
                comics = it
            )
        }

        val lastWeek = comics.value?.let {
            ComicsCollection(
                title = "Last Week",
                comics = it
            )
        }

        val thisMonth = comics.value?.let {
            ComicsCollection(
                title = "This Month",
                comics = it
            )
        }

        _screenItems.postValue(
            listOfNotNull(
                thisWeek,
                nextWeek,
                lastWeek,
                thisMonth
            )
        )
    }

    override fun onComicClicked(comic: ComicsResponse) {}
}