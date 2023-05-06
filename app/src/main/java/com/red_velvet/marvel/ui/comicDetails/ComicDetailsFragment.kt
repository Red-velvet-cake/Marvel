package com.red_velvet.marvel.ui.comicDetails

import android.util.Log
import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.databinding.FragmentComicDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.comicDetails.adapter.ParentComicAdapter

class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_comic_details

    override val viewModel: ComicDetailsViewModel by viewModels()
    override fun setUp() {
        observeData()
    }

    private fun observeData() {
        viewModel.comicsDetails.observe(viewLifecycleOwner) { state ->
            (state as? State.Success)?.let { comicsDetailsState ->
                viewModel.creators.observe(viewLifecycleOwner) { creatorsState ->
                    (creatorsState as? State.Success)?.let { creators ->
                        viewModel.characters.observe(viewLifecycleOwner) { charactersState ->
                            (charactersState as? State.Success)?.let { characters ->
                                setUpParentAdapter(creators.toData(), characters.toData())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setUpParentAdapter(
        creators: List<CreatorsResponse>?,
        characters: List<CharactersResponse>?
    ) {
        val parentItems = listOf(
            ParentComicItem(
                "Creators",
                creators
            ),
            ParentComicItem(
                "Characters",
                characters
            )
        )

        val parentAdapter = ParentComicAdapter(parentItems, viewModel)
        binding.recyclerViewParent.adapter = parentAdapter
    }


}

