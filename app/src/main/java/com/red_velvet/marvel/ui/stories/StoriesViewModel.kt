package com.red_velvet.marvel.ui.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.StoryResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseInteractionListener
import com.red_velvet.marvel.ui.base.BaseViewModel


class StoriesViewModel : BaseViewModel(), BaseInteractionListener {
    private val repository = MarvelRepositoryImpl(RetrofitClient.apiService)
    private val _stories: MutableLiveData<State<List<StoryResponse>>> = MutableLiveData()
    val stories1: LiveData<State<List<StoryResponse>>> get() = _stories

    fun getAllStories() {
        bindStateUpdates(
            repository.getStories(),
            onError = ::onGetStoriesError,
            onNext = ::onGetStoriesSuccess
        )
    }

    private fun onGetStoriesSuccess(state: State<List<StoryResponse>?>) {
        _stories.postValue(State.Loading)
        state.toData()?.let {
            _stories.postValue(State.Success(it))
        }
    }

    private fun onGetStoriesError(error: Throwable) {
        _stories.postValue(State.Failed(error.message.toString()))
    }
}