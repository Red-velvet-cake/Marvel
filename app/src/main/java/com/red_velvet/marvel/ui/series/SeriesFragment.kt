package com.red_velvet.marvel.ui.series

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentSeriesBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class SeriesFragment : BaseFragment<FragmentSeriesBinding, SeriesViewModel>() {

    override val layoutIdFragment = R.layout.fragment_series

    override val viewModel: SeriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SeriesAdapter(emptyList(), viewModel)
        binding.recyclerViewSeries.adapter = adapter

        setUpChipsListener()
        viewModel.searchQuery.observe(viewLifecycleOwner) {
            setUpChipsListener(it)
        }

        initNavigateToSeriesDetails()
    }

    private fun initNavigateToSeriesDetails() {
        viewModel.navigationToSeriesDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    SeriesFragmentDirections.actionSeriesFragmentToSeriesDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }

    private fun setUpChipsListener(searchQuery: String? = null) {
        binding.chips.chipAllSeries.setOnClickListener {
            viewModel.getAllSeries()
        }
        binding.chips.chipComic.setOnClickListener {
            viewModel.getAllSeries(searchQuery, binding.chips.chipComic.text.toString())
        }
        binding.chips.chipMagazine.setOnClickListener {
            viewModel.getAllSeries(searchQuery, binding.chips.chipMagazine.text.toString())
        }
        binding.chips.chipHardcover.setOnClickListener {
            viewModel.getAllSeries(searchQuery, binding.chips.chipHardcover.text.toString())
        }
        binding.chips.chipTradePaperback.setOnClickListener {
            viewModel.getAllSeries(searchQuery, binding.chips.chipTradePaperback.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.searchQuery.removeObservers(this)
    }
}





