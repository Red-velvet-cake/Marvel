package com.red_velvet.marvel.ui.comicDetails

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentComicDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding, ComicDetailsViewModel>() {

    override val layoutIdFragment = R.layout.fragment_comic_details

    override val viewModel: ComicDetailsViewModel by viewModels()

    private val args: ComicDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadComicDetails(args.comicId)
    }


    override fun setUp() {
        val creatorsAdapter = ComicDetailsCreatorsAdapter(mutableListOf(), viewModel)
        val charactersAdapter = ComicDetailsCharactersAdapter(mutableListOf(), viewModel)

        binding.apply {
            recyclerViewComicCreators.adapter = creatorsAdapter
            recyclerViewComicCharacters.adapter = charactersAdapter
        }

        binding.textViewError.setOnClickListener { viewModel.loadComicDetails(args.comicId) }

        navigateToCharacterDetails()
    }

    private fun navigateToCharacterDetails() {
        viewModel.navigationToCharacterDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    ComicDetailsFragmentDirections.actionComicDetailsFragmentToCharacterFragment(it)
                findNavController().navigate(directions)
            }
        }
    }
}