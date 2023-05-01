package com.red_velvet.marvel.ui.comicDetails

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.databinding.FragmentComicDetailsBinding
import com.red_velvet.marvel.databinding.FragmentHomeBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_comic_details

    override val viewModel: ComicDetailsViewModel by viewModels()

    override fun setUp() {

        viewModel.comics.observe(this) {
            when (it) {
                is State.Failed -> {
                    binding.pBar.visibility = View.VISIBLE
                }

                State.Loading -> {
                }

                is State.Success -> {
                    Log.d("Mako", "setUp:  ")
                    binding.pBar.visibility = View.GONE
                }
            }
        }


    }
}

