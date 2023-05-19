package com.red_velvet.marvel.ui.storyDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.domain.repository.MarvelRepository
import com.red_velvet.marvel.data.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.State

class StoryDetailsViewModel : BaseViewModel(), StoryCreatorInteractionListener {

    private val _story: MutableLiveData<State<List<Story>>> = MutableLiveData()
    val story: LiveData<State<List<Story>>> = _story

    private val _comics: MutableLiveData<State<List<Comic>>> = MutableLiveData()
    val comics: LiveData<State<List<Comic>>> = _comics

    private val _creators: MutableLiveData<State<List<Creator>>> = MutableLiveData()
    val creators: LiveData<State<List<Creator>>> = _creators

    private val repository: MarvelRepository by lazy {
        MarvelRepositoryImpl(RetrofitClient.apiService)
    }

    fun loadStoryDetails(storyId: Int) {
        getStoryById(storyId)
        getComicsByStoryId(storyId)
        getCreatorsBySeriesId(storyId)
    }

    private fun getStoryById(storyId: Int) {
        bindStateUpdates(
            repository.getStoryById(storyId),
            onNext = ::onGetStoryState,
            onError = ::onGetStoryFailure
        )
    }

    private fun getComicsByStoryId(storyId: Int) {
        bindStateUpdates(
            repository.getComicsByStoryId(storyId),
            onNext = ::onGetComicsState,
            onError = ::onGetComicsFailure
        )
    }

    private fun getCreatorsBySeriesId(storyId: Int) {
        bindStateUpdates(
            repository.getCreatorsBySeriesId(storyId),
            onNext = ::onGetCreatorState,
            onError = ::onGetSeriesFailure
        )
    }

    private fun onGetSeriesFailure(error: Throwable) {
        _creators.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetCreatorState(state: State<List<Creator>>) {
        _creators.postValue(state)
    }

    private fun onGetComicsFailure(error: Throwable) {
        _comics.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetComicsState(state: State<List<Comic>>) {
        _comics.postValue(state)
    }

    private fun onGetStoryFailure(error: Throwable) {
        _story.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetStoryState(state: State<List<Story>>) {
        _story.postValue(state)
    }

}