package com.red_velvet.marvel.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.databinding.FragmentHomeBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutIdFragment = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModels()

    val marvelRepositoryImpl = MarvelRepositoryImpl(RetrofitClient.apiService)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnClickListener {

            marvelRepositoryImpl.getStory(116).subscribe({
                println("TAG: success: $it")
                Log.i("TAG", "success: $it")
            }, {
                Log.i("TAG", "error: $it")
            })
        }

        val dis = MarvelRepositoryImpl(RetrofitClient.apiService).getComicDetail(82970).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe({
            Log.d("TAG", "onViewCreated: ${it.attributionText} ")

        }, {
            Log.d("TAG", "onViewCreated: ${it.message} ")

        })

    }
}

