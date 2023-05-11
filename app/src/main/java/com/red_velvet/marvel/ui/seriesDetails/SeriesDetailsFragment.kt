package com.red_velvet.marvel.ui.seriesDetails

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentSeriesDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding, SeriesDetailsViewModel>() {

    override val layoutIdFragment = R.layout.fragment_series_details

    private val args: SeriesDetailsFragmentArgs by navArgs()
    override val viewModel: SeriesDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadSeriesDetails(args.seriesId)
    }

    override fun setUp() {
        val creatorsAdapter = CreatorsAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSerieCreators.adapter = creatorsAdapter
        binding.textViewError.setOnClickListener { viewModel.loadSeriesDetails(args.seriesId) }
    }
}