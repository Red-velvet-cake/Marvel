package com.red_velvet.marvel.ui.comicDetails

import android.os.Bundle
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnClickListener {
            setUp()
            binding.lifecycleOwner = viewLifecycleOwner

        }
    }
    override fun setUp() {
        observeComicsDetails()
        observeCharacters()
        observeCreators()
    }

    private fun observeComicsDetails() {
        viewModel.comicsDetails.observe(viewLifecycleOwner) {
            when (it) {
                is State.Failed -> {
                    binding.pBar.visibility = View.VISIBLE
                    Log.d("Tag", "Failed:observeComicsDetails:  ")

                }

                State.Loading -> {
                }

                is State.Success -> {
                    binding.pBar.visibility = View.GONE
                    Log.d("Tag", "Success:observeComicsDetails:  ")
                }
            }
        }
    }

    private fun observeCharacters() {
        viewModel.characters.observe(viewLifecycleOwner) {
            when (it) {
                is State.Failed -> {
                    Log.d("Tag", "Failed:observeCharacters:  ")

                }

                State.Loading -> {
                }

                is State.Success -> {
                    Log.d("Tag", "Success:observeCharacters:  ")
                }
            }
        }
    }

    private fun observeCreators() {
        viewModel.creators.observe(viewLifecycleOwner) {
            when (it) {
                is State.Failed -> {
                    Log.d("Tag", "Failed:observeCreators:  ")

                }

                State.Loading -> {
                }

                is State.Success -> {
                    Log.d("Tag", "Success:observeCreators:  ")
                }
            }
        }
    }


}

