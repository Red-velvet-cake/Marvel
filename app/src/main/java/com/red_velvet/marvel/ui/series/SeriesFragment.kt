package com.red_velvet.marvel.ui.series

import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentSeriesBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class SeriesFragment : BaseFragment<FragmentSeriesBinding>() {

    override val layoutIdFragment = R.layout.fragment_series

    override val viewModel: SeriesViewModel by viewModels()

    override fun setUp() {
        viewModel.getAllSeries()
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

    }
}





