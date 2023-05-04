package com.red_velvet.marvel.ui.comicDetails

import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentComicDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.comicDetails.adapter.ComicDetailsCharactersAdapter
import com.red_velvet.marvel.ui.comicDetails.adapter.ComicDetailsCreatorsAdapter

class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_comic_details

    override val viewModel: ComicDetailsViewModel by viewModels()

    override fun setUp() {
        viewModel.fetchData(1749)
        val creatorsAdapter = ComicDetailsCreatorsAdapter(mutableListOf(), viewModel)
        val charactersAdapter = ComicDetailsCharactersAdapter(mutableListOf(), viewModel)

        binding.recyclerViewComicCreators.adapter = creatorsAdapter
        binding.recyclerViewComicCharacters.adapter = charactersAdapter

    }
}

