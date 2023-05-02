package com.red_velvet.marvel.ui.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.StoryResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel

class StoriesViewModel : BaseViewModel() {
    val repository = MarvelRepositoryImpl(RetrofitClient.apiService)
    private val _Stories: MutableLiveData<State<List<StoryResponse>>> = MutableLiveData()
    val stories: LiveData<State<List<StoryResponse>>> get() = _Stories

    init {
        getStories()
    }

    private fun getStories() {


    }

    private fun onGetStoriesError(error: Throwable) {
        _Stories.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetStoriesSuccess(response: BaseResponse<StoryResponse>) {
        _Stories.postValue(State.Success(response.data?.results!!))
    }
}