package com.red_velvet.marvel.ui.storyDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.data.remote.dtos.ComicDto
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryDetailsViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    BaseViewModel(), StoryCreatorInteractionListener {

    private val _story: MutableLiveData<State<List<Story>>> = MutableLiveData()
    val story: LiveData<State<List<Story>>> = _story

    private val _comics: MutableLiveData<State<List<ComicDto>>> = MutableLiveData()
    val comics: LiveData<State<List<ComicDto>>> = _comics

    private val _creators: MutableLiveData<State<List<Creator>>> = MutableLiveData()
    val creators: LiveData<State<List<Creator>>> = _creators

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

    private fun onGetComicsState(state: State<List<ComicDto>>) {
        _comics.postValue(state)
    }

    private fun onGetStoryFailure(error: Throwable) {
        _story.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetStoryState(state: State<List<Story>>) {
        _story.postValue(state)
    }

}