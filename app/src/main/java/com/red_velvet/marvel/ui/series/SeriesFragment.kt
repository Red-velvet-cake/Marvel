package com.red_velvet.marvel.ui.series

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentSeriesBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class SeriesFragment : BaseFragment<FragmentSeriesBinding, SeriesViewModel>() {

    override val layoutIdFragment = R.layout.fragment_series

    override val viewModel: SeriesViewModel by viewModels()

    override fun setUp() {
        val adapter = SeriesAdapter(emptyList(), viewModel)
        binding.recyclerViewSeries.adapter = adapter

        binding.chips.chipAllSeries.setOnClickListener {
            viewModel.getAllSeries()
        }
        binding.chips.chipComic.setOnClickListener {
            viewModel.filterSeries(binding.chips.chipComic.text.toString())
        }
        binding.chips.chipMagazine.setOnClickListener {
            viewModel.filterSeries(binding.chips.chipMagazine.text.toString())
        }
        binding.chips.chipHardcover.setOnClickListener {
            viewModel.filterSeries(binding.chips.chipHardcover.text.toString())
        }
        binding.chips.chipTradePaperback.setOnClickListener {
            viewModel.filterSeries(binding.chips.chipTradePaperback.text.toString())
        }

        navigateToSeriesDetails()
    }

    private fun navigateToSeriesDetails() {
        viewModel.navigationToSeriesDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    SeriesFragmentDirections.actionSeriesFragmentToSeriesDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.searchQuery.removeObservers(this)
    }
}





