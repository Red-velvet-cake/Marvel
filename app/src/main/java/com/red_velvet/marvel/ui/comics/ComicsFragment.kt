package com.red_velvet.marvel.ui.comics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentComicsBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class ComicsFragment : BaseFragment<FragmentComicsBinding, ComicsViewModel>() {

    override val layoutIdFragment = R.layout.fragment_comics

    override val viewModel: ComicsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val comicsAdapter = ComicsScreenAdapter(emptyList(), viewModel)
        binding.recyclerComics.adapter = comicsAdapter
        binding.textViewError.setOnClickListener {
            viewModel.apply {
                getThisWeekComics()
                getNextWeekComics()
                getLastWeekComics()
                getThisMonthComics()
            }
        }
        initNavigateToComicDetails()
    }

    private fun initNavigateToComicDetails() {
        viewModel.navigationToComicDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    ComicsFragmentDirections.actionComicsFragmentToComicDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }

}