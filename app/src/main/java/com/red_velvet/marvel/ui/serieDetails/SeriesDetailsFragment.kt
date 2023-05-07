package com.red_velvet.marvel.ui.serieDetails

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentSeriesDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.serieDetails.adapter.CreatorsAdapter

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_series_details

    private val args: SeriesDetailsFragmentArgs by navArgs()
    override val viewModel: SeriesDetailsViewModel by viewModels()
    override fun setUp() {
        val seriesId = args.seriesId
        viewModel.getSeries(seriesId)
        viewModel.getCreators(seriesId)
        val creatorsAdapter = CreatorsAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSerieCreators.adapter = creatorsAdapter
    }
}