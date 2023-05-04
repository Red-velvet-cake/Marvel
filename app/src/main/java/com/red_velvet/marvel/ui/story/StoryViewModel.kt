package com.red_velvet.marvel.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.StoryResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel

class StoryViewModel :BaseViewModel(){
    private val _story: MutableLiveData<State<List<StoryResponse>>> = MutableLiveData()
    val story: LiveData<State<List<StoryResponse>>> = _story

    private val _comics: MutableLiveData<State<List<ComicsResponse>>> = MutableLiveData()
    val comics: LiveData<State<List<ComicsResponse>>> get() = _comics


    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)
    init {
//        bindStateUpdates(_story,repository.getStory(8))
//        bindStateUpdates(_comics,repository.getComicsByStoryId(8))

    }
}