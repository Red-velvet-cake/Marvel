package com.red_velvet.marvel.ui.comics

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentComicsBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class ComicsFragment : BaseFragment<FragmentComicsBinding>() {

    override val layoutIdFragment = R.layout.fragment_comics

    override val viewModel: ComicsViewModel by viewModels()

    override fun setUp() {
        val comicsAdapter = ComicsScreenAdapter(emptyList(), viewModel)
        binding.recyclerComics.adapter = comicsAdapter
        navigateToComicDetails()
    }

    private fun navigateToComicDetails() {
        viewModel.navigationToComicDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    ComicsFragmentDirections.actionComicsFragmentToComicDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }

}