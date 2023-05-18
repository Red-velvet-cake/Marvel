package com.red_velvet.marvel.ui.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.remote.dto.StoryDto
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State


class StoriesViewModel : BaseViewModel(), StoriesInteractionListener {

    private val repository by lazy { MarvelRepositoryImpl(RetrofitClient.apiService) }

    private val _navigationToStoryDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToStoryDetails: LiveData<SingleEvent<Int>> = _navigationToStoryDetails

    private val _stories: MutableLiveData<State<List<StoryDto>>> = MutableLiveData()
    val stories: LiveData<State<List<StoryDto>>> = _stories

    init {
        getAllStories()
    }

    fun getAllStories() {
        bindStateUpdates(
            repository.getAllStories(),
            onError = ::onGetStoriesFailure,
            onNext = ::onGetStoriesState
        )
    }

    private fun onGetStoriesState(state: State<List<StoryDto>>) {
        _stories.postValue(state)
    }

    private fun onGetStoriesFailure(error: Throwable) {
        _stories.postValue(State.Failed(error.message.toString()))
    }

    override fun doOnStoryClicked(storyId: Int) {
        _navigationToStoryDetails.postValue(SingleEvent(storyId))
    }
}