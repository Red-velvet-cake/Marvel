package com.red_velvet.marvel.ui.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State


class StoriesViewModel : BaseViewModel(), StoriesInteractionListener {

    private val _navigationToStoryDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToStoryDetails: LiveData<SingleEvent<Int>> = _navigationToStoryDetails

    private val repository = MarvelRepositoryImpl(RetrofitClient.apiService)
    private val _stories: MutableLiveData<State<List<Story>>> = MutableLiveData()
    val stories: LiveData<State<List<Story>>> get() = _stories

    init {
        getAllStories()
    }

    fun getAllStories() {
        bindStateUpdates(
            repository.getStories(),
            onError = ::onGetStoriesError,
            onNext = ::onGetStoriesSuccess
        )
    }

    private fun onGetStoriesSuccess(state: State<List<Story>>) {
        _stories.postValue(state)
    }

    private fun onGetStoriesError(error: Throwable) {
        _stories.postValue(State.Failed(error.message.toString()))
    }

    override fun onStoryClicked(storyId: Int) {
        _navigationToStoryDetails.postValue(SingleEvent(storyId))
    }
}