package com.red_velvet.marvel.ui.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.StoryResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel


class StoriesViewModel : BaseViewModel() {
    private val repository = MarvelRepositoryImpl(RetrofitClient.apiService)
    private val _stories: MutableLiveData<State<List<StoryResponse>>> = MutableLiveData()
    val stories: LiveData<State<List<StoryResponse>>> get() = _stories

    init {
        getStories()
    }

     fun getStories() {
         getData(_stories, repository.getStories())
     }
}