package com.red_velvet.marvel.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentHomeBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.home.adapter.CharactersAdapter
import com.red_velvet.marvel.ui.home.adapter.ComicsAdapter
import com.red_velvet.marvel.ui.home.adapter.EventsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val layoutIdFragment = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val comicsAdapter = ComicsAdapter(emptyList(), viewModel)
        val eventsAdapter = EventsAdapter(emptyList(), viewModel)
        val charsAdapter = CharactersAdapter(emptyList(), viewModel)

        binding.apply {
            recyclerComics.adapter = comicsAdapter
            recyclerChars.adapter = charsAdapter
            recyclerEvents.adapter = eventsAdapter
        }
//        binding.textViewError.setOnClickListener {
//            viewModel.apply {
////                getCharacters()
//                getComics()
////                getEvents()
//            }
//        }
        initNavigateToComicDetails()
    }

    private fun initNavigateToComicDetails() {
        viewModel.navigationToComicDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    HomeFragmentDirections.actionHomeFragmentToComicDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }

        viewModel.navigationToEventDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    HomeFragmentDirections.actionHomeFragmentToEventDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }

        viewModel.navigationToCharacterDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    HomeFragmentDirections.actionHomeFragmentToCharacterFragment(it)
                findNavController().navigate(directions)
            }
        }
    }
}