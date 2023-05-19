package com.red_velvet.marvel.ui.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    BaseViewModel(), StoriesInteractionListener {


    private val _navigationToStoryDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToStoryDetails: LiveData<SingleEvent<Int>> = _navigationToStoryDetails

    private val _stories: MutableLiveData<State<List<Story>>> = MutableLiveData()
    val stories: LiveData<State<List<Story>>> = _stories

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

    private fun onGetStoriesState(state: State<List<Story>>) {
        _stories.postValue(state)
    }

    private fun onGetStoriesFailure(error: Throwable) {
        _stories.postValue(State.Failed(error.message.toString()))
    }

    override fun doOnStoryClicked(storyId: Int) {
        _navigationToStoryDetails.postValue(SingleEvent(storyId))
    }
}